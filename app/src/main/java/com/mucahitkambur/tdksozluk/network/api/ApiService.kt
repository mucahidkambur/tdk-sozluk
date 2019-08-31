package com.mucahitkambur.tdksozluk.network.api

import com.mucahitkambur.tdksozluk.model.search.Suggestion
import com.mucahitkambur.tdksozluk.model.main.ContentResult
import com.mucahitkambur.tdksozluk.model.search.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("icerik")
    fun getContent(): Call<ContentResult>

    @GET("gts")
    fun getSearchContent(@Query("ara") word: String): Call<List<SearchResult>>

    @GET("autocomplete.json")
    fun getSuggestions(): Call<List<Suggestion>>
}