package com.mucahitkambur.tdksozluk.ui.search


import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mucahitkambur.tdksozluk.R
import com.mucahitkambur.tdksozluk.adapter.SearchDetailAlphabetAdapter
import com.mucahitkambur.tdksozluk.adapter.SearchDetailMeaningsAdapter
import com.mucahitkambur.tdksozluk.adapter.SearchDetailProverbAdapter
import com.mucahitkambur.tdksozluk.databinding.FragmentSearchDetailBinding
import com.mucahitkambur.tdksozluk.di.Injectable
import com.mucahitkambur.tdksozluk.util.*
import javax.inject.Inject

class SearchDetailFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SearchViewModel

    private lateinit var dataBinding: FragmentSearchDetailBinding

    private lateinit var args: SearchDetailFragmentArgs

    private lateinit var proverbAdapter: SearchDetailProverbAdapter

    private var alphabetAdapter: SearchDetailAlphabetAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = viewModelProvider(viewModelFactory)
        args = SearchDetailFragmentArgs.fromBundle(arguments!!)
        dataBinding = DataBindingUtil.inflate<FragmentSearchDetailBinding>(
            inflater,
            R.layout.fragment_search_detail,
            container,
            false
        ).apply {
            isVisible = false

        }
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dataBinding.toolbarCommon.setOnClickListener{
            findNavController().navigate(SearchDetailFragmentDirections.actionNavSearchDetailToNavSearch())
        }

        observeSearch()
        viewModel.searchWord(args.word)

        proverbAdapter = SearchDetailProverbAdapter({
            viewModel.searchWord(it.madde)
        })

        alphabetAdapter = SearchDetailAlphabetAdapter()
        dataBinding.recycSearchAlphabet.adapter = alphabetAdapter
    }

    private fun observeSearch(){
        viewModel.searchResult.observe(this, EventObserver {
            if (it.status == Status.SUCCESS){
                dataBinding.searchContent = it.data?.get(0)

                val meanList = it.data!![0].anlamlarListe
                val proverbList = it.data[0].atasozu
                val alphabetList = alphabetPerCharacter(args.word)

                proverbAdapter.setProverbs(proverbList)
                dataBinding.recycSearchProverb.adapter = proverbAdapter

                alphabetAdapter?.setAlphabets(alphabetList)

                dataBinding.recycSearchMean.adapter = SearchDetailMeaningsAdapter(meanList)

                dataBinding.isVisible = true
            } else if (it.status == Status.ERROR){
                showError(it.message?.message)
                startSearch()
            }
        })
    }
}
