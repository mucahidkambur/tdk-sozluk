package com.mucahitkambur.tdksozluk.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "table_suggestions")
data class Suggestion(

    @SerializedName("madde")
    @ColumnInfo(name = "kelime")
    @PrimaryKey
    var madde: String
)