package com.mucahitkambur.tdksozluk.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.mucahitkambur.tdksozluk.R
import com.mucahitkambur.tdksozluk.ui.search.SearchDetailFragmentDirections
import com.mucahitkambur.tdksozluk.util.findNavController
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationUI.setupWithNavController(bottom_nav, findNavController(R.id.fragment_container))
    }

    override fun onBackPressed() {
        if (isDestinationSearchDetail()) {
            findNavController(R.id.fragment_container).navigate(SearchDetailFragmentDirections.actionNavSearchDetailToNavSearch())
        }
    }

    fun isDestinationSearchDetail(): Boolean{
        return navController().currentDestination?.id == R.id.nav_search
    }


    private fun navController() = findNavController(R.id.fragment_container)

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
