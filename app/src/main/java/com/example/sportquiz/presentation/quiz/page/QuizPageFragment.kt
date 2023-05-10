package com.example.sportquiz.presentation.quiz.page

import android.os.Bundle
import android.view.View
import com.example.sportquiz.databinding.FragmentQuizPageBinding
import com.example.sportquiz.model.Quiz
import com.example.sportquiz.presentation.common.fragment.BaseFragment
import com.example.sportquiz.source.QuizSource

class QuizPageFragment : BaseFragment<FragmentQuizPageBinding>(
    FragmentQuizPageBinding::inflate
) {
    var page: Int = 0

    var currentQuiz: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments?.getInt(Factory.ArgPage) ?: return
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val quizzes = QuizSource.getQuizzes(page)

        quizzes.firstOrNull()?.let(::setUpQuizData)
    }

    private fun setUpQuizData(quiz: Quiz) = with(binding) {
        txtTitle.text = quiz.title
        btnA.text = quiz.a
        btnB.text = quiz.b
        btnC.text = quiz.c
        btnD.text = quiz.d
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