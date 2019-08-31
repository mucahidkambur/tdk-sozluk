package com.mucahitkambur.tdksozluk.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mucahitkambur.tdksozluk.model.search.SearchResult
import com.mucahitkambur.tdksozluk.repositories.SearchRepository
import com.mucahitkambur.tdksozluk.util.Event
import com.mucahitkambur.tdksozluk.util.Resource
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private var searchRepository: SearchRepository
) : ViewModel(){

    private val _searchWord = MutableLiveData<Event<String>>()

    val searchResult: LiveData<Event<Resource<List<SearchResult>>>> = Transformations
        .switchMap(_searchWord) {searchRepository.searchContent(it.peekContent())}

    fun searchWord(word: String){
        _searchWord.value = Event(word)
    }
}