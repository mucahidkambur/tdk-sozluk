package com.mucahitkambur.tdksozluk.di

import com.mucahitkambur.tdksozluk.ui.main.MainFragment
import com.mucahitkambur.tdksozluk.ui.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeSearch(): SearchFragment
}