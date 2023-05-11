package com.example.sportquiz

import com.example.sportquiz.di.DaggerAppComponent
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class App : DaggerApplication()  {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this@App)
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        Firebase.remoteConfig.setConfigSettingsAsync(configSettings)
        Firebase.remoteConfig.fetchAndActivate()
    }


    override fun applicationInjector(): AndroidInjector<out  DaggerApplication> =
        DaggerAppComponent.builder()
            .application(this)
            .build()

}
