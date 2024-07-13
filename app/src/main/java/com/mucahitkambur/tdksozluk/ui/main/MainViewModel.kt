package com.mucahitkambur.tdksozluk.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.mucahitkambur.tdksozluk.model.search.Suggestion
import com.mucahitkambur.tdksozluk.model.main.ContentResult
import com.mucahitkambur.tdksozluk.model.main.Kural
import com.mucahitkambur.tdksozluk.model.main.Url
import com.mucahitkambur.tdksozluk.repositories.MainRepository
import com.mucahitkambur.tdksozluk.util.Event
import com.mucahitkambur.tdksozluk.util.Resource
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    private val _mainContent = MutableLiveData<Event<Unit>>()
    private val _webViewClick = MutableLiveData<Url>()
    private val _textClick = MutableLiveData<String>()

    val mainContentResult: LiveData<Event<Resource<ContentResult>>> = _mainContent
        .switchMap { repository.mainContent() }

    fun mainContent(){
        _mainContent.value = Event(Unit)
    }

    fun suggestionsContentResult(): LiveData<Event<Resource<List<Suggestion>>>>{
        return repository.suggestionsContent()
    }

    fun webviewResult() : MutableLiveData<Url> {
        return _webViewClick
    }

    fun startWebview(url: Kural){
        _webViewClick.value = Url(url.url, url.adi)
    }

    fun textClickResult(): MutableLiveData<String> {
        return _textClick
    }

    fun textClick(text: String){
        _textClick.value = text
    }
}