package com.mucahitkambur.tdksozluk.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.mucahitkambur.tdksozluk.model.search.History
import com.mucahitkambur.tdksozluk.model.search.SearchResult
import com.mucahitkambur.tdksozluk.model.search.Suggestion
import com.mucahitkambur.tdksozluk.network.api.*
import com.mucahitkambur.tdksozluk.network.api.ApiException
import com.mucahitkambur.tdksozluk.network.local.AppDatabase
import com.mucahitkambur.tdksozluk.util.AppExecutors
import com.mucahitkambur.tdksozluk.util.Event
import com.mucahitkambur.tdksozluk.util.Resource
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val database: AppDatabase,
    private val appExecutors: AppExecutors,
    private val apiService: ApiService
) {
    private val searchContentResult = MediatorLiveData<Event<Resource<List<SearchResult>>>>()

    fun searchContent(word: String): LiveData<Event<Resource<List<SearchResult>>>> {
        appExecutors.networkIO().execute {
            try{
                val response = apiService.getSearchContent(word).execute()
                when (val apiResponse = ApiResponse.create(response)) {
                    is ApiSuccessResponse -> {
                        searchContentResult.postValue(Event(Resource.success(apiResponse.body)))
                        insertWordToHistoryDb(History(0,apiResponse.body.get(0).madde))
                    }
                    is ApiErrorResponse -> {
                        searchContentResult.postValue(Event(Resource.error(apiResponse.errorMessage, null)))
                    }
                }
            }catch (exception: Exception) {
                val apiException = ApiException.create(exception)
                searchContentResult.postValue(
                    Event(
                        Resource.error(
                            apiException,
                            null
                        )
                    )
                )
            }
        }

        return searchContentResult
    }

    fun insertWordToHistoryDb(history: History){
        database.historyDao().insert(history)
    }

    fun getHistoryFromDb(): LiveData<List<History>>{
        return database.historyDao().getHistory()
    }

    fun getSuggestionFromDb(word: String?): LiveData<List<Suggestion>> {
        return database.suggestionDao().getSuggestions(word)
    }

    fun deleteSearchHistory(){
        appExecutors.diskIO().execute{
            database.historyDao().deleteAll()
        }
    }
}