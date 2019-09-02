package com.mucahitkambur.tdksozluk.model

import com.google.gson.annotations.SerializedName

data class Result(
    @field:SerializedName("error")
    val message: String = String()
)