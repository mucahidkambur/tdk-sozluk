package com.mucahitkambur.tdksozluk.network.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mucahitkambur.tdksozluk.model.Suggestion

@Database(entities = [Suggestion::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun suggestionDao(): SuggestionDao
}