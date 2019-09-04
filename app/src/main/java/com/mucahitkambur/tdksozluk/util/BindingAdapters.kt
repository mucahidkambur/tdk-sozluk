package com.mucahitkambur.tdksozluk.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mucahitkambur.tdksozluk.BuildConfig
import com.mucahitkambur.tdksozluk.R

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("visibleInvisible")
    fun visibleInvisible(view: View, isVisible: Boolean){
        if (isVisible)
            view.visibility = View.VISIBLE
        else
            view.visibility  = View.INVISIBLE
    }

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun visibleGone(view: View, isVisible: Boolean){
        if (isVisible)
            view.visibility = View.VISIBLE
        else
            view.visibility  = View.GONE
    }

    @JvmStatic
    @BindingAdapter("htmlToText")
    fun htmlToText(view: TextView, text: String?){
        text?.let {
            view.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY);
        }
    }

    @JvmStatic
    @BindingAdapter("alphabetImage")
    fun alphabetImage(view: ImageView, text: String?){
        val url = view.context.getString(R.string.alphabet_ext, BuildConfig.ALPHABET_IMG_URL, text)
        Glide.with(view.context)
            .load(url)
            .into(view)
    }
}