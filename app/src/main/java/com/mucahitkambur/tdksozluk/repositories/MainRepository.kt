package com.mucahitkambur.tdksozluk.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.mucahitkambur.tdksozluk.model.search.Suggestion
import com.mucahitkambur.tdksozluk.model.main.ContentResult
import com.mucahitkambur.tdksozluk.network.api.ApiErrorResponse
import com.mucahitkambur.tdksozluk.network.api.ApiResponse
import com.mucahitkambur.tdksozluk.network.api.ApiService
import com.mucahitkambur.tdksozluk.network.api.ApiSuccessResponse
import com.mucahitkambur.tdksozluk.network.local.AppDatabase
import com.mucahitkambur.tdksozluk.util.AppExecutors
import com.mucahitkambur.tdksozluk.util.Event
import com.mucahitkambur.tdksozluk.util.Resource
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val database: AppDatabase,
    private val appExecutors: AppExecutors,
    private val apiService: ApiService
){
    private val contentResult = MediatorLiveData<Event<Resource<ContentResult>>>()
    private val suggestionsContent = MediatorLiveData<Event<Resource<List<Suggestion>>>>()


    fun mainContent(): LiveData<Event<Resource<ContentResult>>> {
        appExecutors.networkIO().execute {
            val response = apiService.getContent().execute()
            when(val apiResponse = ApiResponse.create(response)){
                is ApiSuccessResponse -> {
                    contentResult.postValue(Event(Resource.success(apiResponse.body)))
                }
                is ApiErrorResponse -> {
                    contentResult.postValue(Event(Resource.error(apiResponse.errorMessage, null)))
                }
                else -> Unit
            }
        }
        return contentResult
    }

    fun suggestionsContent(): LiveData<Event<Resource<List<Suggestion>>>> {
        appExecutors.networkIO().execute {
            val response = apiService.getSuggestions().execute()
            when(val apiResponse = ApiResponse.create(response)){
                is ApiSuccessResponse -> {
                    suggestionsContent.postValue(Event(Resource.success(apiResponse.body)))
                    insertSuggestionToDb(apiResponse.body)
                }
                is ApiErrorResponse -> {
                    suggestionsContent.postValue(Event(Resource.error(apiResponse.errorMessage, null)))
                }
                else -> Unit
            }
        }
        return suggestionsContent
    }

    fun insertSuggestionToDb(suggestions: List<Suggestion>) {
        appExecutors.diskIO().execute {
            database.suggestionDao().insert(suggestions)
        }
    }
}