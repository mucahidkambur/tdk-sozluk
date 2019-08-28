package com.mucahitkambur.tdksozluk.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mucahitkambur.tdksozluk.model.Suggestion
import com.mucahitkambur.tdksozluk.model.ContentResult
import com.mucahitkambur.tdksozluk.model.SuggestionSingleton
import com.mucahitkambur.tdksozluk.repositories.MainRepository
import com.mucahitkambur.tdksozluk.util.Event
import com.mucahitkambur.tdksozluk.util.Resource
import javax.inject.Inject
import com.mucahitkambur.tdksozluk.util.START_RULE_WEBVIEW


class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    private val _mainContent = MutableLiveData<Event<Unit>>()
    private val _click = MutableLiveData<Pair<Int, String>>()

    val mainContentResult: LiveData<Event<Resource<ContentResult>>> = Transformations
        .switchMap(_mainContent){ repository.mainContent() }

    fun mainContent(){
        _mainContent.value = Event(Unit)
    }

    fun suggestionsContentResult(): LiveData<Event<Resource<List<Suggestion>>>>{
        return repository.suggestionsContent()
    }

    fun suggestionsDbResult() : LiveData<List<Suggestion>> {
        return repository.getSuggestionsFromDb()
    }

    fun webviewResult() : MutableLiveData<Pair<Int, String>> {
        return _click
    }

    fun startWebview(value: String){
        _click.value = Pair(START_RULE_WEBVIEW, value)
    }
}