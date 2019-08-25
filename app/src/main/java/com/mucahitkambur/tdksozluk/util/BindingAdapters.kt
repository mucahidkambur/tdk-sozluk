package com.mucahitkambur.tdksozluk.util

import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("htmlToText")
    fun htmlToText(view: TextView, text: String?){
        text?.let {
            view.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY);
        }
    }
}