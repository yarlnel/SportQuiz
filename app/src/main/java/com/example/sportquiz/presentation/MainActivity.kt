package com.example.sportquiz.presentation

import android.os.Bundle
import android.widget.Toast
import com.example.sportquiz.R
import com.example.sportquiz.presentation.common.backpress.BackPressedStrategyOwner
import com.example.sportquiz.presentation.navigation.graph.Screens
import com.example.sportquiz.presentation.utils.CloakingUtils
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private val navigator: Navigator = AppNavigator(this, R.id.main_root)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getPreferences(MODE_PRIVATE)

        var url = prefs.getString(Pref.Url, "")
        if (url.isNullOrEmpty()) {
            url = Firebase.remoteConfig.getString("url")
            prefs.edit().putString(Pref.Url, url).apply()
        }

        if (CloakingUtils.checkIsEmu() || url.isBlank())
            router.navigateTo(Screens.Home())
        else
            router.navigateTo(Screens.Cloaka(url))
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
        } else if (supportFragmentManager.fragments.size == 1) {
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    object Pref {
        const val Url = "coloaka_url"
    }
}