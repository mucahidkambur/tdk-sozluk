package com.mucahitkambur.tdksozluk.network.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mucahitkambur.tdksozluk.model.Autocomplete

@Dao
interface AutocompDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insert(objects: List<Autocomplete>?)

    @Query("SELECT * FROM table_autocomp")
    fun getAutocomp(): LiveData<List<Autocomplete>>
}