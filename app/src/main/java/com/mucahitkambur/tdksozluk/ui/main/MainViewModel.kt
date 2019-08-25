package com.mucahitkambur.tdksozluk.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mucahitkambur.tdksozluk.model.Autocomplete
import com.mucahitkambur.tdksozluk.model.ContentResult
import com.mucahitkambur.tdksozluk.repositories.MainRepository
import com.mucahitkambur.tdksozluk.util.Event
import com.mucahitkambur.tdksozluk.util.Resource
import javax.inject.Inject

class MainViewModel @Inject constructor(repository: MainRepository): ViewModel() {

    private val _content = MutableLiveData<Event<Unit>>()
    private val _autocompl = MutableLiveData<Event<Unit>>()

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
}