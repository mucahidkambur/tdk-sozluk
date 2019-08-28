package com.mucahitkambur.tdksozluk.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mucahitkambur.tdksozluk.model.Autocomplete
import com.mucahitkambur.tdksozluk.model.ContentResult
import com.mucahitkambur.tdksozluk.model.WordSingleton
import com.mucahitkambur.tdksozluk.network.local.AppDatabase
import com.mucahitkambur.tdksozluk.repositories.MainRepository
import com.mucahitkambur.tdksozluk.util.Event
import com.mucahitkambur.tdksozluk.util.Resource
import javax.inject.Inject
import com.mucahitkambur.tdksozluk.util.START_RULE_WEBVIEW


class MainViewModel @Inject constructor(
    repository: MainRepository,
    private val database: AppDatabase,
    private val wordSingleton: WordSingleton
): ViewModel() {

    private val _content = MutableLiveData<Event<Unit>>()
    private val _autocompl = MutableLiveData<Event<Unit>>()
    private val _autocomplDb = MutableLiveData<Unit>()
    private val clickData = MutableLiveData<Pair<Int, String>>()

    init {
        repository.getAutocompFromDb(
            {

            }
        )
    }

    val contenResult: LiveData<Event<Resource<ContentResult>>> = Transformations
        .switchMap(_content){ repository.icerik() }

    fun content(){
        _content.value = Event(Unit)
    }

    val autocompResult: LiveData<Event<Resource<List<Autocomplete>>>> = Transformations
        .switchMap(_autocompl){ repository.autocomplete() }

    fun autocomplete(){
        _autocompl.value = Event(Unit)
    }

    val autoCompleteDbResult: LiveData<List<Autocomplete>> = Transformations
        .switchMap(_autocomplDb) {repository.getAutocompFromDb()}

    fun autocompleteDb(){
        _autocomplDb.value = (Unit)
    }

    fun webviewResult() : MutableLiveData<Pair<Int, String>> {
        return clickData
    }

    fun startWebWiew(value: String){
        clickData.value = Pair(START_RULE_WEBVIEW, value)
    }
}