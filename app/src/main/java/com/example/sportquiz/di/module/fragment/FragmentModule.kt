package com.example.sportquiz.di.module.fragment

import com.example.sportquiz.presentation.cloaka.CloakaFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun cloakaFragment(): CloakaFragment
}
