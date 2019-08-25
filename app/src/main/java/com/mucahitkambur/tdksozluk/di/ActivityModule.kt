package com.mucahitkambur.tdksozluk.di

import com.mucahitkambur.tdksozluk.ui.MainActivity
import com.mucahitkambur.tdksozluk.ui.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity


    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeSplashActivity(): SplashActivity
}