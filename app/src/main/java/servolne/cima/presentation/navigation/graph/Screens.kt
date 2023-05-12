package servolne.cima.presentation.navigation.graph

import servolne.cima.presentation.cloaka.CloakaFragment
import servolne.cima.presentation.quiz.congratulations.CongratulationsFragment
import servolne.cima.presentation.quiz.home.HomeFragment
import servolne.cima.presentation.quiz.page.QuizPageFragment
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