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
    fun View.visibleInvisible(isVisible: Boolean) {
        visibility = if (isVisible)
            View.VISIBLE
        else
            View.INVISIBLE
    }

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun View.visibleGone(isVisible: Boolean) {
        visibility = if (isVisible)
            View.VISIBLE
        else
            View.GONE
    }

    @JvmStatic
    @BindingAdapter("htmlToText")
    fun TextView.htmlToText(text: String?) {
        text?.let {
            setText(HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY))
        }
    }

    @JvmStatic
    @BindingAdapter("alphabetImage")
    fun ImageView.alphabetImage(text: String?) {
        val url = this.context.getString(R.string.alphabet_ext, BuildConfig.ALPHABET_IMG_URL, text)
        Glide.with(context)
            .load(url)
            .into(this)
    }
}