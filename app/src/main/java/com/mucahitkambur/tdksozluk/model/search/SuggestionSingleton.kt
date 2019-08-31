package com.mucahitkambur.tdksozluk.model.search

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SuggestionSingleton @Inject constructor(){

    var suggestions: List<Suggestion>? = null
}