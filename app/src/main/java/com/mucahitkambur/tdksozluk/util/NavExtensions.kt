package com.mucahitkambur.tdksozluk.util

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.mucahitkambur.tdksozluk.ui.MainActivity
import com.mucahitkambur.tdksozluk.ui.WebViewActivity

fun FragmentActivity.startHomeActivity(){
    startActivity(MainActivity.newIntent(this))
}

fun Fragment.startWebView(url: String){
    startActivity(WebViewActivity.newIntent(requireContext(), url))
}