package com.mucahitkambur.tdksozluk.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WordSingleton @Inject constructor(){

    var autocomplete: MutableLiveData<List<Autocomplete>>? = null
}