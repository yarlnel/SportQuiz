package com.example.sportquiz.di.module.fragment

import com.example.sportquiz.presentation.cloaka.CloakaFragment
import com.example.sportquiz.presentation.quiz.page.QuizPageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun cloakaFragment(): CloakaFragment

    @ContributesAndroidInjector
    fun quizPageFragment(): QuizPageFragment
}
