package com.mucahitkambur.tdksozluk.network.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mucahitkambur.tdksozluk.model.Autocomplete

@Database(entities = [Autocomplete::class], version = 2)
abstract class AppDatabase : RoomDatabase(){

    abstract fun autocompDao(): AutocompDao
}