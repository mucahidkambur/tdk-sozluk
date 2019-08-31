package com.mucahitkambur.tdksozluk.model.search

import com.google.gson.annotations.SerializedName

data class Atasozu(
    @field:SerializedName("madde")
    val madde: String,
    @field:SerializedName("madde_id")
    val madde_id: String
)