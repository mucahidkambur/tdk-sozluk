package com.mucahitkambur.tdksozluk.repositories

import androidx.lifecycle.LiveData
import com.mucahitkambur.tdksozluk.model.favorites.Favorite
import com.mucahitkambur.tdksozluk.network.api.ApiService
import com.mucahitkambur.tdksozluk.network.local.AppDatabase
import com.mucahitkambur.tdksozluk.util.AppExecutors
import javax.inject.Inject

class FavoritesRepository @Inject constructor(
    private val database: AppDatabase
) {

    fun getFavorites(): LiveData<List<Favorite>> {
        return database.favoriteDao().getFavorites()
    }
}