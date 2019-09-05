package com.mucahitkambur.tdksozluk.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mucahitkambur.tdksozluk.model.search.History
import com.mucahitkambur.tdksozluk.model.search.SearchResult
import com.mucahitkambur.tdksozluk.model.search.Suggestion
import com.mucahitkambur.tdksozluk.repositories.SearchRepository
import com.mucahitkambur.tdksozluk.util.Event
import com.mucahitkambur.tdksozluk.util.Resource
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private var repository: SearchRepository
) : ViewModel(){

    private val _searchWord = MutableLiveData<Event<String>>()
    private val _suggestionWord = MutableLiveData<String>()

    val searchResult: LiveData<Event<Resource<List<SearchResult>>>> = Transformations
        .switchMap(_searchWord) {repository.searchContent(it.peekContent())}

    fun searchWord(word: String){
        _searchWord.value = Event(word)
    }

    val suggestionsDbResult: LiveData<List<Suggestion>> = Transformations
        .switchMap(_suggestionWord) {repository.getSuggestionFromDb(it)}

    fun suggestionWord(word: String?){
        _suggestionWord.value = word
    }

    fun historyDbResult() : LiveData<List<History>> {
        return repository.getHistoryFromDb()
    }

    fun deleteHistory(){
        repository.deleteSearchHistory()
    }
}