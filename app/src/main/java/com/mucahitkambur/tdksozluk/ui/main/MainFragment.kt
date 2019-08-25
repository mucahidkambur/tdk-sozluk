package com.mucahitkambur.tdksozluk.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mucahitkambur.tdksozluk.R
import com.mucahitkambur.tdksozluk.adapter.PageAdapter
import com.mucahitkambur.tdksozluk.databinding.FragmentMainBinding
import com.mucahitkambur.tdksozluk.di.Injectable
import com.mucahitkambur.tdksozluk.network.local.AppDatabase
import com.mucahitkambur.tdksozluk.util.AppExecutors
import com.mucahitkambur.tdksozluk.util.EventObserver
import com.mucahitkambur.tdksozluk.util.Status
import com.mucahitkambur.tdksozluk.util.viewModelProvider
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
        }
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        viewModel.content()
        observeIcerik()
    }

    private fun initView(){

        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val dimensionPadding = resources.getDimensionPixelSize(R.dimen.margin_large)
        val dimensionMargin = resources.getDimensionPixelSize(R.dimen.margin_medium)
        pager_mixed.setPadding(dimensionPadding / 2, 0, dimensionPadding, 0)
        pager_mixed.pageMargin = dimensionMargin
        pager_mistakes.setPadding(dimensionPadding / 2, 0, dimensionPadding, 0)
        pager_mistakes.pageMargin = dimensionMargin
    }

    private fun observeIcerik(){
        viewModel.contenResult.observe(this, EventObserver {
            if (it.status == Status.SUCCESS){
                it.data?.let {
                    dataBinding.content = it
                    pager_mixed.adapter = PageAdapter(it.karistirma)
                    pager_mistakes.adapter = PageAdapter(it.syyd)
                }

            }else if (it.status == Status.ERROR){

            }
        })
    }
}
