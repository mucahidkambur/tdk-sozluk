package com.mucahitkambur.tdksozluk

import android.app.Activity
import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.mucahitkambur.tdksozluk.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App: Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        MobileAds.initialize(this) {}
    }

    override fun activityInjector() = dispatchingAndroidInjector
}