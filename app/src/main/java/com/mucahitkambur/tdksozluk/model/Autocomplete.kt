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

@Entity(tableName = "table_autocomp")
data class Autocomplete(

    @SerializedName("madde")
    @ColumnInfo(name = "kelime")
    @PrimaryKey
    var madde: String = "",
    var isHistory: Boolean = false

) : SearchSuggestion {


    constructor(suggestion: String) : this() {
        this.madde = suggestion.toLowerCase()
    }

    constructor(source: Parcel) : this() {
        this.madde = source.readString().toString()
        this.isHistory = source.readInt() != 0
    }

    override fun getBody(): String? {
        return madde
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(madde)
        dest.writeInt(if (isHistory) 1 else 0)
    }

    companion object CREATOR: Parcelable.Creator<Autocomplete>  {
        override fun createFromParcel(`in`: Parcel): Autocomplete {
            return Autocomplete(`in`)
        }

        override fun newArray(size: Int): Array<Autocomplete?>? {
            return arrayOfNulls(size)
        }
    }
}