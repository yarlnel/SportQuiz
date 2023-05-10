package com.example.sportquiz.presentation.quiz.page

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sportquiz.R
import com.example.sportquiz.databinding.FragmentQuizPageBinding
import com.example.sportquiz.model.Quiz
import com.example.sportquiz.presentation.common.fragment.BaseFragment
import com.example.sportquiz.presentation.utils.onclick
import com.example.sportquiz.source.QuizSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuizPageFragment : BaseFragment<FragmentQuizPageBinding>(
    FragmentQuizPageBinding::inflate
) {
    var page: Int = 0

    var currentQuiz: Int = 0
    val quizzes = mutableListOf<Quiz>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments?.getInt(Factory.ArgPage) ?: return
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            quizzes += QuizSource.getQuizzes(page)

            withContext(Dispatchers.Main) {
                quizzes.firstOrNull()?.let(::setUpQuizData)
            }
        }
    }

    private fun setUpQuizData(quiz: Quiz) = with(binding) {
        // TODO
        image.setImageResource(R.drawable.img_for_quiz_page_2)
        txtTitle.text = quiz.title
        btnA.text = quiz.a
        btnB.text = quiz.b
        btnC.text = quiz.c
        btnD.text = quiz.d

        val buttons = mapOf(
            Quiz.Answer.A to btnA,
            Quiz.Answer.B to btnB,
            Quiz.Answer.C to btnC,
            Quiz.Answer.D to btnD
        )

        for ((answerType, btn) in buttons) {
            if (answerType == quiz.answer)
                btn onclick ::onAnswerTrue
            else
                btn onclick ::onAnswerFalse
        }
    }

    private fun onAnswerTrue() {
        currentQuiz++
        setUpQuizData(quizzes[currentQuiz])
    }

    private fun onAnswerFalse() {
        Toast.makeText(requireContext(), "False", Toast.LENGTH_SHORT).show()
    }

    object Factory {
        const val ArgPage = "arg_page"

        fun newInstance(page: Int) = QuizPageFragment().apply {
            arguments = Bundle().apply {
                putInt(ArgPage, page)
            }
        }
    }
}