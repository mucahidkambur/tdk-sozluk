package com.mucahitkambur.tdksozluk.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mucahitkambur.tdksozluk.R
import com.mucahitkambur.tdksozluk.adapter.PageAdapter
import com.mucahitkambur.tdksozluk.databinding.FragmentMainBinding
import com.mucahitkambur.tdksozluk.di.Injectable
import com.mucahitkambur.tdksozluk.network.local.AppDatabase
import com.mucahitkambur.tdksozluk.util.*
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    @Inject
    lateinit var database: AppDatabase

    private lateinit var layoutManager: LinearLayoutManager

    private lateinit var viewModel: MainViewModel

    private lateinit var dataBinding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = viewModelProvider(viewModelFactory)
        dataBinding = DataBindingUtil.inflate<FragmentMainBinding>(
            inflater,
            R.layout.fragment_main,
            container,
            false
        ).apply {
            lifecycleOwner = this@MainFragment
            mainViewModel = viewModel
        }
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        viewModel.mainContent()
        observeMainContent()
        observeWebView()
    }

    private fun initView(){

        card_rule.setOnClickListener{

        }

        //SwipeLayout
        swipe_main.setProgressViewOffset(false, resources.getDimensionPixelSize(R.dimen.refresher_offset),
            resources.getDimensionPixelSize(R.dimen.refresher_offset_end))
        swipe_main.setOnRefreshListener { viewModel.mainContent(); }

        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val dimensionPadding = resources.getDimensionPixelSize(R.dimen.margin_large)
        val dimensionMargin = resources.getDimensionPixelSize(R.dimen.margin_medium)
        pager_mixed.setPadding(dimensionPadding / 2, 0, dimensionPadding, 0)
        pager_mixed.pageMargin = dimensionMargin
        pager_mistakes.setPadding(dimensionPadding / 2, 0, dimensionPadding, 0)
        pager_mistakes.pageMargin = dimensionMargin
    }

    private fun observeMainContent(){
        viewModel.mainContentResult.observe(this, EventObserver {
            if (it.status == Status.SUCCESS){
                it.data?.let {
                    dataBinding.content = it
                    dataBinding.isVisible = true
                    pager_mixed.adapter = PageAdapter(it.karistirma)
                    pager_mistakes.adapter = PageAdapter(it.syyd)
                    swipe_main.isRefreshing = false
                }
            }else if (it.status == Status.LOADING)
                dataBinding.isVisible = false
            else if (it.status == Status.ERROR){
                dataBinding.isVisible = true
                showError(it.message)
            }
        })
    }

    private fun observeWebView(){
        viewModel.webviewResult().observe(this, Observer {
            when (it.first) {
                START_RULE_WEBVIEW -> startWebView(it.second)
            }
        })
    }
}
