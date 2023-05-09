package com.example.sportquiz.presentation

import android.os.Bundle
import com.example.sportquiz.R
import com.example.sportquiz.presentation.common.backpress.BackPressedStrategyOwner
import com.example.sportquiz.presentation.navigation.graph.Screens
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private val navigator: Navigator = AppNavigator(this, R.id.main_root)

    //    {
//
//        override fun applyCommands(commands: Array<out Command>) {
//            super.applyCommands(commands)
//            supportFragmentManager.executePendingTransactions()
//        }
//    }
//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        router.navigateTo(Screens.Cloaka())
    }


    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    fun ultimateOnBackPressed() = super.onBackPressed()

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.isEmpty())
            super.onBackPressed()

        val lastFragment = supportFragmentManager.fragments.lastOrNull()
        if (lastFragment is BackPressedStrategyOwner) {
            lastFragment.handleBackPress()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}