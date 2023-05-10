package com.example.sportquiz.presentation.navigation.graph

import com.example.sportquiz.model.Quiz
import com.example.sportquiz.presentation.cloaka.CloakaFragment
import com.example.sportquiz.presentation.quiz.page.QuizPageFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun Cloaka() = FragmentScreen {
        CloakaFragment()
    }

    fun QuizPage(page: Int) = FragmentScreen {
        QuizPageFragment.Factory.newInstance(page)
    }
}