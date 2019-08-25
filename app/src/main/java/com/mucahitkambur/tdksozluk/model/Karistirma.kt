package com.mucahitkambur.tdksozluk.model

import com.google.gson.annotations.SerializedName

data class Karistirma(
    @field:SerializedName("id" )
    val id: String,
    @field:SerializedName("yanlis", alternate= ["yanliskelime"])
    val yanlis: String,
    @field:SerializedName("dogru", alternate= ["dogrukelime"])
    val dogru: String
)