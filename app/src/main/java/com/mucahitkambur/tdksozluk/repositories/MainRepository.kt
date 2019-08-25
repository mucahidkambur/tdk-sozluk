package com.mucahitkambur.tdksozluk.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.mucahitkambur.tdksozluk.model.Autocomplete
import com.mucahitkambur.tdksozluk.model.ContentResult
import com.mucahitkambur.tdksozluk.network.api.ApiErrorResponse
import com.mucahitkambur.tdksozluk.network.api.ApiResponse
import com.mucahitkambur.tdksozluk.network.api.ApiService
import com.mucahitkambur.tdksozluk.network.api.ApiSuccessResponse
import com.mucahitkambur.tdksozluk.util.AppExecutors
import com.mucahitkambur.tdksozluk.util.Event
import com.mucahitkambur.tdksozluk.util.Resource
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val apiService: ApiService
){
    private val icerikResult = MediatorLiveData<Event<Resource<ContentResult>>>()
    private val autocompResult = MediatorLiveData<Event<Resource<List<Autocomplete>>>>()


    fun icerik(): LiveData<Event<Resource<ContentResult>>> {
        appExecutors.networkIO().execute {
            val response = apiService.getContent().execute()
            when(val apiResponse = ApiResponse.create(response)){
                is ApiSuccessResponse -> {
                    icerikResult.postValue(Event(Resource.success(apiResponse.body)))
                }
                is ApiErrorResponse -> {
                    icerikResult.postValue(Event(Resource.error(apiResponse.errorMessage, null)))
                }
            }
        }
        return icerikResult
    }

    fun autocomplete(): LiveData<Event<Resource<List<Autocomplete>>>> {
        appExecutors.networkIO().execute {
            val response = apiService.getAutocomp().execute()
            when(val apiResponse = ApiResponse.create(response)){
                is ApiSuccessResponse -> {
                    autocompResult.postValue(Event(Resource.success(apiResponse.body)))
                }
                is ApiErrorResponse -> {
                    autocompResult.postValue(Event(Resource.error(apiResponse.errorMessage, null)))
                }
            }
        }
        return autocompResult
    }
}