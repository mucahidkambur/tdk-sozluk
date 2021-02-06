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
import com.mucahitkambur.tdksozluk.network.local.AppDatabase
import javax.inject.Inject
import com.mucahitkambur.tdksozluk.model.search.Suggestion
import com.mucahitkambur.tdksozluk.util.*


class SearchFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    @Inject
    lateinit var database: AppDatabase

    private lateinit var searchAdapter: SearchAdapter
    private lateinit var historyAdapter: HistoryAdapter

    private lateinit var viewModel: SearchViewModel

    private lateinit var dataBinding: FragmentSearchBinding

    private var searchText: String? = null

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
            viewModel = this@SearchFragment.viewModel
        }
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeHistory()
        observeSuggestions()
    }

    private fun initView() {
        dataBinding.viewSearch.searchEditText.showKeyboard()
        dataBinding.viewSearch.visibility = View.VISIBLE

        dataBinding.cardCappedA.setOnClickListener {
            dataBinding.viewSearch.searchEditText.text.append(resources.getString(R.string.capped_a))
        }

        dataBinding.cardCappedI.setOnClickListener {
            dataBinding.viewSearch.searchEditText.text.append(resources.getString(R.string.capped_i))
        }

        dataBinding.cardCappedU.setOnClickListener {
            dataBinding.viewSearch.searchEditText.text.append(resources.getString(R.string.capped_u))
        }

        historyAdapter = HistoryAdapter()
        searchAdapter = SearchAdapter()
        searchAdapter.suggestionClick = {
            hideKeyboard()
            startSearchDetail(it.madde)
        }
        dataBinding.recycHistory.adapter = historyAdapter
        dataBinding.recycSuggestion.adapter = searchAdapter
        dataBinding.recycSuggestion.addItemDecoration(divider())
        dataBinding.recycHistory.addItemDecoration(divider())

        dataBinding.viewSearch.setOnQueryTextListener(object :
            SimpleSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchText = null
                    hideKeyboard()
                    startSearchDetail(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchText = newText
                viewModel.suggestionWord(newText)
                if (newText.isNullOrEmpty()) {
                    dataBinding.linearHistory.visibility = View.VISIBLE
                } else {
                    dataBinding.recycSuggestion.visibility = View.VISIBLE
                    dataBinding.linearHistory.visibility = View.GONE
                }
                return false
            }

            override fun onQueryTextCleared(): Boolean {
                return false
            }
        })
    }

    private fun observeHistory() {
        viewModel.historyDbResult().observe(viewLifecycleOwner, Observer {
            historyAdapter.historyClick = {
                startSearchDetail(it.word)
            }
            historyAdapter.deleteClick = {
                viewModel.deleteHistoryById(it.id)
            }
            historyAdapter.items = it
        })
    }

    private fun observeSuggestions() {
        viewModel.suggestionsDbResult.observe(viewLifecycleOwner, Observer {
            if (searchText.isNullOrEmpty()) {
                dataBinding.recycSuggestion.visibility = View.GONE
            } else {
                dataBinding.recycSuggestion.visibility = View.VISIBLE
            }

            searchAdapter.setSuggestions(it)
        })
    }
}
