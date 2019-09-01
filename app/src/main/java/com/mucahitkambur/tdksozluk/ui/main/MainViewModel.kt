package com.mucahitkambur.tdksozluk.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mucahitkambur.tdksozluk.model.search.Suggestion
import com.mucahitkambur.tdksozluk.model.main.ContentResult
import com.mucahitkambur.tdksozluk.model.main.Kural
import com.mucahitkambur.tdksozluk.model.main.Url
import com.mucahitkambur.tdksozluk.repositories.MainRepository
import com.mucahitkambur.tdksozluk.util.Event
import com.mucahitkambur.tdksozluk.util.Resource
import javax.inject.Inject
import com.mucahitkambur.tdksozluk.util.START_RULE_WEBVIEW


class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    private val _mainContent = MutableLiveData<Event<Unit>>()
    private val _click = MutableLiveData<Url>()

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

    fun webviewResult() : MutableLiveData<Url> {
        return _click
    }

    fun startWebview(url: Kural){
        _click.value = Url(url.url, url.adi)
    }
}