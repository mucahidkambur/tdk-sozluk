package com.mucahitkambur.tdksozluk.util

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface PreferenceStorage {
    var searchCount: Int
    var observableSearchCount: LiveData<Int>
}

class SharedPreferenceStorage @Inject constructor(context: Context) :
    PreferenceStorage {

    private val prefs: Lazy<SharedPreferences> = lazy { // Lazy to prevent IO access to main thread.
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).apply {
            registerOnSharedPreferenceChangeListener(changeListener)
        }
    }

    private val observableSearchCountResult = MutableLiveData<Int>()

    private val changeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key == PREF_COUNT) {
            observableSearchCountResult.value = searchCount
        }
    }

    override var searchCount: Int by IntPreference(prefs, PREF_COUNT, 0)

    override var observableSearchCount: LiveData<Int>
        get()  {
            observableSearchCountResult.value = searchCount
            return observableSearchCountResult
        }
        set(value) = throw IllegalAccessException("This property can't be changed")


    companion object {
        const val PREFS_NAME = "user_pref"
        const val PREF_COUNT = "pref_count"
    }
}

class IntPreference(
    private val preferences: Lazy<SharedPreferences>,
    private val name: String,
    private val defaultValue: Int
) : ReadWriteProperty<Any, Int> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): Int {
        return preferences.value.getInt(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        preferences.value.edit().putInt(name, value).apply()
    }
}