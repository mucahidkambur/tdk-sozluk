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
            viewModel = this@MainFragment.viewModel
            isVisible = false
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

        //SwipeLayout
        dataBinding.swipeMain.setProgressViewOffset(false, resources.getDimensionPixelSize(R.dimen.refresher_offset), resources.getDimensionPixelSize(R.dimen.refresher_offset_end))
        dataBinding.swipeMain.setOnRefreshListener { viewModel.mainContent(); }

        //ViewPagers
        val dimensionPadding = resources.getDimensionPixelSize(R.dimen.margin_large)
        val dimensionMargin = resources.getDimensionPixelSize(R.dimen.margin_medium)
        dataBinding.pagerMixed.setPadding(dimensionPadding / 2, 0, dimensionPadding, 0)
        dataBinding.pagerMixed.pageMargin = dimensionMargin
        dataBinding.pagerMistakes.setPadding(dimensionPadding / 2, 0, dimensionPadding, 0)
        dataBinding.pagerMistakes.pageMargin = dimensionMargin
    }

    private fun observeMainContent(){
        viewModel.mainContentResult.observe(this, EventObserver {
            if (it.status == Status.SUCCESS){
                it.data?.let {

                    dataBinding.content = it
                    dataBinding.isVisible = true

                    dataBinding.pagerMixed.adapter = PageAdapter(it.karistirma) {
                        startSearchDetail(it)
                    }
                    dataBinding.pagerMistakes.adapter = PageAdapter(it.syyd) {

                    }

                    dataBinding.swipeMain.isRefreshing = false
                }
            } else if (it.status == Status.ERROR){
                showError(it.message?.message)
            }
        })
    }

    private fun observeWebView(){
        viewModel.webviewResult().observe(this, Observer {
            startWebView(it)
        })
    }
}
