package com.mucahitkambur.tdksozluk.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mucahitkambur.tdksozluk.BuildConfig
import com.mucahitkambur.tdksozluk.R
import com.mucahitkambur.tdksozluk.model.favorites.Favorite

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
        val url = context.getString(R.string.alphabet_ext, BuildConfig.ALPHABET_IMG_URL, text)
        Glide.with(context)
            .load(url)
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("isFavorited")
    fun ImageView.isFavorited(favorite: Favorite?) {
        val glide = Glide.with(context)
        if (favorite == null) {
            glide.load(R.drawable.ic_favorite_empty).into(this)
        } else {
            glide.load(R.drawable.ic_favorites).into(this)
        }
    }
}