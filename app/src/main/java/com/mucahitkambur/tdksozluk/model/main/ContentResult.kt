package com.mucahitkambur.tdksozluk.model.main

import com.google.gson.annotations.SerializedName

data class ContentResult(
    @field:SerializedName("karistirma")
    val karistirma: List<Karistirma>,
    @field:SerializedName("atasoz")
    val atasoz: List<Atasoz>,
    @field:SerializedName("syyd")
    val syyd: List<Karistirma>,
    @field:SerializedName("kural")
    val kural: List<Kural>,
    @field:SerializedName("yabanci")
    val yabanci: Yabanci,
    @field:SerializedName("kelime")
    val kelime: List<Kelime>
)