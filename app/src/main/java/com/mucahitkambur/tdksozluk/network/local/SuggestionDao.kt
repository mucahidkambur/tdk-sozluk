package com.mucahitkambur.tdksozluk.network.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mucahitkambur.tdksozluk.model.Suggestion

@Dao
interface SuggestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insert(objects: List<Suggestion>?)

    @Query("SELECT * FROM table_suggestions")
    fun getSuggestions(): LiveData<List<Suggestion>>
}