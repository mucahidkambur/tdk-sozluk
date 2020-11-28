package com.mucahitkambur.tdksozluk.network.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mucahitkambur.tdksozluk.model.favorites.Favorite
import com.mucahitkambur.tdksozluk.model.search.History
import com.mucahitkambur.tdksozluk.model.search.Suggestion

@Database(entities = [Suggestion::class, History::class, Favorite::class], version = 2)
abstract class AppDatabase : RoomDatabase(){

    abstract fun suggestionDao(): SuggestionDao
    abstract fun historyDao(): HistoryDao
    abstract fun favoriteDao(): FavoriteDao

}