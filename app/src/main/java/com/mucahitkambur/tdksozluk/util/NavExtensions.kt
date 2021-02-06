package com.mucahitkambur.tdksozluk.util

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.mucahitkambur.tdksozluk.R
import com.mucahitkambur.tdksozluk.model.main.Url
import com.mucahitkambur.tdksozluk.ui.MainActivity
import com.mucahitkambur.tdksozluk.ui.WebViewActivity
import com.mucahitkambur.tdksozluk.ui.search.SearchFragmentDirections

fun FragmentActivity.startHomeActivity(){
    startActivity(MainActivity.newIntent(this))
    finish()
}

fun Fragment.startWebView(url: Url){
    startActivity(WebViewActivity.newIntent(requireContext(), url))
}

fun Fragment.startSearch(){
    findNavController().navigate(
        R.id.nav_search
    )
}

fun Fragment.startSearchDetail(word: String){
    hideKeyboard()
    findNavController().navigate(
        SearchFragmentDirections.actionSearchDetail(
            word
        )
    )
}