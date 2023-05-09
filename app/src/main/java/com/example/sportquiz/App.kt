package com.example.sportquiz

import com.example.sportquiz.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class App : DaggerApplication()  {


    override fun applicationInjector(): AndroidInjector<out  DaggerApplication> =
        DaggerAppComponent.builder()
            .application(this)
            .build()

}
