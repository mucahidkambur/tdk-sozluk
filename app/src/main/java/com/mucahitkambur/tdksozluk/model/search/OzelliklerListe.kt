package com.mucahitkambur.tdksozluk.model.search

import com.google.gson.annotations.SerializedName

data class OzelliklerListe(
    @field:SerializedName("ekno")
    val ekno: String,
    @field:SerializedName("kisa_adi")
    val kisa_adi: String,
    @field:SerializedName("ozellik_id")
    val ozellik_id: String,
    @field:SerializedName("tam_adi")
    val tam_adi: String,
    @field:SerializedName("tur")
    val tur: String
)