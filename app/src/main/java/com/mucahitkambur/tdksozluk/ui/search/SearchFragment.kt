package com.mucahitkambur.tdksozluk.ui.search


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ferfalk.simplesearchview.SimpleSearchView

import com.mucahitkambur.tdksozluk.R
import com.mucahitkambur.tdksozluk.adapter.HistoryAdapter
import com.mucahitkambur.tdksozluk.adapter.SearchAdapter
import com.mucahitkambur.tdksozluk.databinding.FragmentSearchBinding
import com.mucahitkambur.tdksozluk.di.Injectable
import com.mucahitkambur.tdksozluk.model.search.SuggestionSingleton
import com.mucahitkambur.tdksozluk.network.local.AppDatabase
import com.mucahitkambur.tdksozluk.util.AppExecutors
import com.mucahitkambur.tdksozluk.util.findNavController
import com.mucahitkambur.tdksozluk.util.viewModelProvider
import javax.inject.Inject


class SearchFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    @Inject
    lateinit var database: AppDatabase

    @Inject
    lateinit var suggestionSingleton: SuggestionSingleton

    private var searchAdapter: SearchAdapter? = null

    private lateinit var viewModel: SearchViewModel

    private lateinit var dataBinding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = viewModelProvider(viewModelFactory)
        dataBinding = DataBindingUtil.inflate<FragmentSearchBinding>(
            inflater,
            R.layout.fragment_search,
            container,
            false
        ).apply {
            lifecycleOwner = this@SearchFragment
        }
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        observeHistory()
    }

    private fun initView(){

        dataBinding.textDeleteHistory.setOnClickListener {
            viewModel.deleteHistory()
        }

        searchAdapter = SearchAdapter(suggestionSingleton.suggestions!!) {
            navigateToSearchDetail(it.madde)
        }

        dataBinding.recycSuggestion.adapter = searchAdapter

        dataBinding.searchView.visibility = View.VISIBLE

        dataBinding.searchView.setOnQueryTextListener(object: SimpleSearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchAdapter?.filter?.filter(newText)
                dataBinding.recycSuggestion.visibility = View.VISIBLE
                dataBinding.linearHistory.visibility = View.GONE
                return false
            }

            override fun onQueryTextCleared(): Boolean {
                return false
            }
        })
    }

    private fun observeHistory(){
        viewModel.historyDbResult().observe(this, Observer {
            if (!it.isNullOrEmpty()){
                dataBinding.linearHistory.visibility = View.VISIBLE
                dataBinding.textDeleteHistory.visibility = View.VISIBLE
            }else
                dataBinding.textDeleteHistory.visibility = View.GONE

            dataBinding.recycHistory.adapter = HistoryAdapter(it, historyClick = {
                navigateToSearchDetail(it.word)
            })

        })
    }

    private fun navigateToSearchDetail(word: String){
        findNavController().navigate(
            SearchFragmentDirections.actionSearchDetail(
                word
            )
        )
    }
}
