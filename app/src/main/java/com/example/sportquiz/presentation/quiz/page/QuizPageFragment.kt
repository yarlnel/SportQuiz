package com.example.sportquiz.presentation.quiz.page

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import com.example.sportquiz.R
import com.example.sportquiz.databinding.FragmentQuizPageBinding
import com.example.sportquiz.model.Quiz
import com.example.sportquiz.presentation.common.fragment.BaseFragment
import com.example.sportquiz.presentation.navigation.graph.Screens
import com.example.sportquiz.presentation.utils.getPageImageRes
import com.example.sportquiz.presentation.utils.onclick
import com.example.sportquiz.source.QuizSource
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuizPageFragment : BaseFragment<FragmentQuizPageBinding>(
    FragmentQuizPageBinding::inflate
), CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext = job + Dispatchers.IO

    private var page: Int = 0
    private var currentQuiz: Int = 1
    private var rightAnswersCount: Int = 0
    private val quizzes = mutableListOf<Quiz>()
    private var haveMistakesOnThisQuestion: Boolean = false

    private val btnBackgroundGreen by lazy {
        ContextCompat.getDrawable(
            requireContext(),
            R.drawable.btn_background_shape
        )
    }

    private val btnBackgroundRed by lazy {
        ContextCompat.getDrawable(
            requireContext(),
            R.drawable.btn_background_shape_red
        )
    }

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments?.getInt(Arg.Page) ?: return
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launch {
            quizzes += QuizSource.getQuizzes(page)

            withContext(Dispatchers.Main) {
                quizzes.firstOrNull()?.let(::setUpQuizData)
                hideLoadingIndicator()
            }
        }
    }

    private fun hideLoadingIndicator() {
        binding.loadingIndicator.isGone = true
    }

    private fun setUpQuizData(quiz: Quiz) = with(binding) {
        listOf(btnA, btnB, btnC, btnD).forEach { btn ->
            btn.background = btnBackgroundGreen
        }

        val imageRes = getPageImageRes(page)
        image.setImageResource(imageRes)

        txtQuizNumber.text = getString(
            R.string.quiz_number_template,
            currentQuiz,
            quizzes.size
        )

        txtSection.text = getString(
            R.string.section_template,
            page
        )

        txtTitle.text = quiz.title
        btnA.text = quiz.a
        btnB.text = quiz.b
        btnC.text = quiz.c
        btnD.text = quiz.d

        val answerToButtons = mapOf(
            Quiz.Answer.A to btnA,
            Quiz.Answer.B to btnB,
            Quiz.Answer.C to btnC,
            Quiz.Answer.D to btnD
        )

        for ((answerType, btn) in answerToButtons) {
            if (answerType == quiz.answer)
                btn onclick ::onAnswerTrue
            else btn onclick {
                onAnswerFalse(btn)
            }
        }
    }

    private fun onAnswerTrue() {
        currentQuiz++
        if (!haveMistakesOnThisQuestion) rightAnswersCount++
        goToNextQuestion()
    }

    private fun onAnswerFalse(btn: Button) {
        haveMistakesOnThisQuestion = true
        btn.background = btnBackgroundRed
    }

    private fun goToNextQuestion() {
        if (currentQuiz == quizzes.lastIndex) {
            router.navigateTo(Screens.Congratulations(
                page = page,
                quizPerPageCount = quizzes.size,
                quizRightAnswerCount = rightAnswersCount
            ))
            return
        }
        haveMistakesOnThisQuestion = false
        setUpQuizData(quizzes[currentQuiz])
    }

    override fun onStop() {
        job.cancel()
        super.onStop()
    }

    object Arg {
        const val Page = "arg_page"
    }

    object Factory {

        fun newInstance(page: Int) = QuizPageFragment().apply {
            arguments = Bundle().apply {
                putInt(Arg.Page, page)
            }
        }
    }
}