package com.example.sportquiz.di.module.activity

import com.example.sportquiz.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ContributesAndroidInjector
    fun mainActivity() : MainActivity
}
