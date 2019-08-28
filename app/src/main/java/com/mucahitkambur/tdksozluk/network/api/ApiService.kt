package com.mucahitkambur.tdksozluk.network.api

import com.mucahitkambur.tdksozluk.model.Suggestion
import com.mucahitkambur.tdksozluk.model.ContentResult
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("icerik")
    fun getContent(): Call<ContentResult>

    @GET("autocomplete.json")
    fun getAutocomp(): Call<List<Suggestion>>
}