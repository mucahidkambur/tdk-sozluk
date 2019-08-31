package com.mucahitkambur.tdksozluk.model.search

import com.google.gson.annotations.SerializedName

data class OrneklerListe(
    @field:SerializedName("anlam_id")
    val anlam_id: String,
    @field:SerializedName("kac")
    val kac: String,
    @field:SerializedName("ornek")
    val ornek: String,
    @field:SerializedName("ornek_id")
    val ornek_id: String,
    @field:SerializedName("ornek_sira")
    val ornek_sira: String,
    @field:SerializedName("yazar_id")
    val yazar_id: String
)