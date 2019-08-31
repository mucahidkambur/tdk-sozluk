package com.mucahitkambur.tdksozluk.model.search

import com.google.gson.annotations.SerializedName

data class AnlamlarListe(
    @field:SerializedName("anlam")
    val anlam: String,
    @field:SerializedName("anlam_id")
    val anlam_id: String,
    @field:SerializedName("anlam_sira")
    val anlam_sira: String,
    @field:SerializedName("fiil")
    val fiil: String,
    @field:SerializedName("gos")
    val gos: String,
    @field:SerializedName("madde_id")
    val madde_id: String,
    @field:SerializedName("orneklerListe")
    val orneklerListe: List<OrneklerListe>,
    @field:SerializedName("ozelliklerListe")
    val ozelliklerListe: List<OzelliklerListe>,
    @field:SerializedName("tipkes")
    val tipkes: String
)