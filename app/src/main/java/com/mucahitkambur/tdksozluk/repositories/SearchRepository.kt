package com.mucahitkambur.tdksozluk.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.mucahitkambur.tdksozluk.model.search.SearchResult
import com.mucahitkambur.tdksozluk.network.api.ApiErrorResponse
import com.mucahitkambur.tdksozluk.network.api.ApiResponse
import com.mucahitkambur.tdksozluk.network.api.ApiService
import com.mucahitkambur.tdksozluk.network.api.ApiSuccessResponse
import com.mucahitkambur.tdksozluk.util.AppExecutors
import com.mucahitkambur.tdksozluk.util.Event
import com.mucahitkambur.tdksozluk.util.Resource
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val apiService: ApiService
) {
    private val searchContentResult = MediatorLiveData<Event<Resource<List<SearchResult>>>>()

    fun searchContent(word: String): LiveData<Event<Resource<List<SearchResult>>>> {
        appExecutors.networkIO().execute {
            val response = apiService.getSearchContent(word).execute()
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    searchContentResult.postValue(Event(Resource.success(apiResponse.body)))
                }
                is ApiErrorResponse -> {
                    searchContentResult.postValue(Event(Resource.error(apiResponse.errorMessage, null)))
                }
            }
        }
        return searchContentResult
    }
}