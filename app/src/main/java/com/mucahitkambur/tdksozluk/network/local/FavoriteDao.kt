package com.mucahitkambur.tdksozluk.network.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mucahitkambur.tdksozluk.model.favorites.Favorite
import com.mucahitkambur.tdksozluk.model.search.History

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    fun insert(favorite: Favorite)

    @Query("SELECT * FROM table_favorites ORDER BY id DESC")
    fun getFavorites(): LiveData<List<Favorite>>

    @Query("SELECT * FROM TABLE_FAVORITES WHERE word = :word")
    fun getFavoriteByWord(word: String) : LiveData<Favorite>

    @Query("DELETE FROM TABLE_FAVORITES WHERE word = :word")
    fun deleteFavoriteByWord(word: String)

}