package com.mucahitkambur.tdksozluk.model

import com.google.gson.annotations.SerializedName

data class Atasoz(
    @field:SerializedName("madde")
    val madde: String,
    @field:SerializedName("anlam")
    val anlam: String
)