package com.example.sportquiz.presentation.quiz.congratulations

import android.os.Bundle
import android.view.View
import com.example.sportquiz.R
import com.example.sportquiz.databinding.FragmentCongratulationsBinding
import com.example.sportquiz.presentation.common.fragment.BaseFragment
import com.example.sportquiz.presentation.navigation.graph.Screens
import com.example.sportquiz.presentation.utils.onclick
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import javax.inject.Inject

class CongratulationsFragment : BaseFragment<FragmentCongratulationsBinding>(
    FragmentCongratulationsBinding::inflate
) {

    @Inject
    lateinit var router: Router

    private var page: Int = 0
    private var quizPerPageCount: Int = 0
    private var quizRightAnswersCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(arguments ?: return) {
            page = getInt(Arg.Page)
            quizPerPageCount = getInt(Arg.QuizPerPageCount)
            quizRightAnswersCount = getInt(Arg.QuizRightAnswersCount)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClickListeners()
        setUpData()
    }

    private fun setUpClickListeners() = with(binding) {
        btnHome onclick ::navigateToHome
    }

    private fun navigateToHome() {
        router.navigateTo(Screens.Home())
    }

    private fun setUpData() = with(binding) {
        txtSection.text = getString(
            R.string.section_template,
            page
        )
        txtTitle.text = getString(
            R.string.congratulations_title_template,
            quizRightAnswersCount,
            quizPerPageCount
        )
    }

    object Arg {
        const val Page = "arg_page_number"
        const val QuizPerPageCount = "quiz_per_page_count"
        const val QuizRightAnswersCount = "quiz_right_answers_count"
    }

    object Factory {

        fun newInstance(
            page: Int,
            quizPerPageCount: Int,
            quizRightAnswersCount: Int
        ) = CongratulationsFragment().apply {
            arguments = Bundle().apply {
                putInt(Arg.Page, page)
                putInt(Arg.QuizPerPageCount, quizPerPageCount)
                putInt(Arg.QuizRightAnswersCount, quizRightAnswersCount)
            }
        }
    }
}