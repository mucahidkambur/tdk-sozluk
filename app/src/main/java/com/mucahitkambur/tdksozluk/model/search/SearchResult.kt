package com.mucahitkambur.tdksozluk.model.search

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @field:SerializedName("anlam_gor")
    val anlam_gor: String,
    @field:SerializedName("anlam_say")
    val anlam_say: String,
    @field:SerializedName("anlamlarListe")
    val anlamlarListe: List<AnlamlarListe>,
    @field:SerializedName("atasozu")
    val atasozu: List<Atasozu>,
    @field:SerializedName("birlesikler")
    val birlesikler: String,
    @field:SerializedName("cesit")
    val cesit: String,
    @field:SerializedName("cesit_say")
    val cesit_say: String,
    @field:SerializedName("cogul_mu")
    val cogul_mu: String,
    @field:SerializedName("font")
    val font: Any,
    @field:SerializedName("gosterim_tarihi")
    val gosterim_tarihi: Any,
    @field:SerializedName("kac")
    val kac: String,
    @field:SerializedName("kelime_no")
    val kelime_no: String,
    @field:SerializedName("lisan")
    val lisan: String,
    @field:SerializedName("lisan_kodu")
    val lisan_kodu: String,
    @field:SerializedName("madde")
    val madde: String,
    @field:SerializedName("madde_duz")
    val madde_duz: String,
    @field:SerializedName("madde_id")
    val madde_id: String,
    @field:SerializedName("on_taki")
    val on_taki: Any,
    @field:SerializedName("ozel_mi")
    val ozel_mi: String,
    @field:SerializedName("taki")
    val taki: Any,
    @field:SerializedName("telaffuz")
    val telaffuz: Any
)