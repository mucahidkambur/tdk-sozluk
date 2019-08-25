package com.mucahitkambur.tdksozluk.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.mucahitkambur.tdksozluk.ui.MainActivity

fun FragmentActivity.startHomeActivity(){
    startActivity(MainActivity.newIntent(this))
}