package com.mucahitkambur.tdksozluk.network.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mucahitkambur.tdksozluk.model.search.History

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    fun insert(history: History)

    @Query("SELECT * FROM table_history ORDER BY id DESC")
    fun getHistory(): LiveData<List<History>>

    @Query("DELETE FROM table_history")
    fun deleteAll()

    @Query("DELETE FROM table_history WHERE id = :id")
    fun deleteHistoryById(id: Int)
}