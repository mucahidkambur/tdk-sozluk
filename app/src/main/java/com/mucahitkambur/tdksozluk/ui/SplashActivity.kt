package com.mucahitkambur.tdksozluk.ui

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mucahitkambur.tdksozluk.model.search.SuggestionSingleton
import com.mucahitkambur.tdksozluk.network.local.AppDatabase
import com.mucahitkambur.tdksozluk.ui.main.MainViewModel
import com.mucahitkambur.tdksozluk.util.*
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var roomDatabase: AppDatabase

    @Inject
    lateinit var appExecutors: AppExecutors

    @Inject
    lateinit var suggestionSingleton: SuggestionSingleton

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)

        if(sharedPreferences.getBoolean(IS_FIRST, true)){
            viewModel.suggestionsContentResult()
            observeSuggestionsFromNetwork()
        }else {
            viewModel.suggestionsDbResult()
            observeSuggestionsFromDb()
        }
    }

    private fun observeSuggestionsFromNetwork(){
        viewModel.suggestionsContentResult().observe(this, EventObserver {
            if(it.status == Status.SUCCESS){
                it.data?.let { suggestions ->
                    suggestionSingleton.suggestions = suggestions
                    sharedPreferences.edit().putBoolean(IS_FIRST, false).apply()
                    startHomeActivity()
                }
            }
        })
    }

    private fun observeSuggestionsFromDb(){
        viewModel.suggestionsDbResult().observe(this, Observer {
            it?.let {
                suggestionSingleton.suggestions = it
                startHomeActivity()
            }
        })
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
