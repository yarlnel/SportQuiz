package com.example.sportquiz.di.module.app

import com.example.sportquiz.di.module.activity.ActivityModule
import com.example.sportquiz.di.module.fragment.FragmentModule
import com.example.sportquiz.di.module.navigation.NavigationModule
import dagger.Module
import dagger.android.AndroidInjectionModule

@Module(includes = [
    ActivityModule::class,
    FragmentModule::class,
    NavigationModule::class
])
interface AppModule
