package com.mucahitkambur.tdksozluk.model.search

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "table_history", indices = arrayOf(Index(value = ["word"], unique = true)))
data class History(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "word")
    val word: String
)