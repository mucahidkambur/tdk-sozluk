package com.mucahitkambur.tdksozluk.model.main

import com.google.gson.annotations.SerializedName

data class Yabanci(
    @field:SerializedName("karsid")
    val karsid: String,
    @field:SerializedName("kkelime")
    val kkelime: String,
    @field:SerializedName("kkoken")
    val kkoken: String,
    @field:SerializedName("kkarsilik")
    val kkarsilik: String,
    @field:SerializedName("anlam")
    val anlam: String
)