package com.mucahitkambur.tdksozluk.model

import com.google.gson.annotations.SerializedName

data class Syyd(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("yanliskelime")
    val yanliskelime: String,
    @field:SerializedName("dogrukelime")
    val dogrukelime: String
)