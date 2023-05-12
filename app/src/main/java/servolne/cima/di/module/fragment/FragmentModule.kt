package servolne.cima.di.module.fragment

import servolne.cima.presentation.cloaka.CloakaFragment
import servolne.cima.presentation.quiz.congratulations.CongratulationsFragment
import servolne.cima.presentation.quiz.home.HomeFragment
import servolne.cima.presentation.quiz.page.QuizPageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun cloakaFragment(): CloakaFragment

    @ContributesAndroidInjector
    fun quizPageFragment(): QuizPageFragment

    @ContributesAndroidInjector
    fun congratulationsFragment(): CongratulationsFragment

    @ContributesAndroidInjector
    fun homeFragment(): HomeFragment
}
