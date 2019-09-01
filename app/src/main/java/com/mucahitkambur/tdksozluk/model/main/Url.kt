package com.mucahitkambur.tdksozluk.model.main

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class Url(
    val url: String,
    val title: String
): Serializable