package com.example.sportquiz.presentation.navigation.graph

import com.example.sportquiz.presentation.cloaka.CloakaFragment
import com.example.sportquiz.presentation.quiz.congratulations.CongratulationsFragment
import com.example.sportquiz.presentation.quiz.home.HomeFragment
import com.example.sportquiz.presentation.quiz.page.QuizPageFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun Cloaka(url: String) = FragmentScreen {
        CloakaFragment.Factory.newInstance(url)
    }

    fun QuizPage(page: Int) = FragmentScreen {
        QuizPageFragment.Factory.newInstance(page)
    }

    fun Congratulations(
        page: Int,
        quizPerPageCount: Int,
        quizRightAnswerCount: Int
    ) = FragmentScreen {
        CongratulationsFragment.Factory.newInstance(
            page,
            quizPerPageCount,
            quizRightAnswerCount
        )
    }

    fun Home() = FragmentScreen {
        HomeFragment()
    }
}