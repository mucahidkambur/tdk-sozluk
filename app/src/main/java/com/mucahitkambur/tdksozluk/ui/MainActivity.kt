package com.mucahitkambur.tdksozluk.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.mucahitkambur.tdksozluk.R
import com.mucahitkambur.tdksozluk.databinding.ActivityMainBinding
import com.mucahitkambur.tdksozluk.ui.search.SearchFragmentDirections
import com.mucahitkambur.tdksozluk.util.PreferenceStorage
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var preferenceStorage: PreferenceStorage

    private lateinit var binding: ActivityMainBinding

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        NavigationUI.setupWithNavController(binding.bottomNav, findNavController(R.id.fragment_container))
    }

    private fun isDestinationSearchDetail(): Boolean{
        return navController().currentDestination?.id == R.id.nav_search_detail
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

        result?.let {
            findNavController(R.id.fragment_container).navigate(
                SearchFragmentDirections.actionSearchDetail(
                    it[0]
                ))
        }
    }

    private fun navController() = findNavController(R.id.fragment_container)

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
