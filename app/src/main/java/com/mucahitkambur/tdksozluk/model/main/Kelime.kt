package com.mucahitkambur.tdksozluk.model.main

import com.google.gson.annotations.SerializedName

data class Kelime(
    @field:SerializedName("madde")
    val madde: String,
    @field:SerializedName("anlam")
    val anlam: String
)