package com.mucahitkambur.tdksozluk.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mucahitkambur.tdksozluk.model.favorites.Favorite
import com.mucahitkambur.tdksozluk.repositories.FavoritesRepository
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val repository: FavoritesRepository
) : ViewModel() {

    fun getFavorites(): LiveData<List<Favorite>> {
        return repository.getFavorites()
    }

}