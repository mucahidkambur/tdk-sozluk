package com.mucahitkambur.tdksozluk.ui.search


import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mucahitkambur.tdksozluk.R
import com.mucahitkambur.tdksozluk.adapter.SearchDetailAlphabetAdapter
import com.mucahitkambur.tdksozluk.adapter.SearchDetailMeaningsAdapter
import com.mucahitkambur.tdksozluk.adapter.SearchDetailProverbAdapter
import com.mucahitkambur.tdksozluk.databinding.FragmentSearchDetailBinding
import com.mucahitkambur.tdksozluk.di.Injectable
import com.mucahitkambur.tdksozluk.model.favorites.Favorite
import com.mucahitkambur.tdksozluk.util.*
import javax.inject.Inject

class SearchDetailFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var preferenceStorage: PreferenceStorage

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
        args = SearchDetailFragmentArgs.fromBundle(requireArguments())
        dataBinding = DataBindingUtil.inflate<FragmentSearchDetailBinding>(
            inflater,
            R.layout.fragment_search_detail,
            container,
            false
        ).apply {
            viewModel = this@SearchDetailFragment.viewModel
            isVisible = false
        }
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addCounter()

        dataBinding.toolbarCommon.setOnClickListener{
            findNavController().navigate(SearchDetailFragmentDirections.actionNavSearchDetailToNavSearch())
        }

        dataBinding.ivFavorite.setOnClickListener {
            if (dataBinding.favorite == null) {
                viewModel.addFavorite(Favorite(0, word = args.word))
            } else {
                viewModel.deleteFavoriteByWord(args.word)
            }
        }

        observeSearch()
        viewModel.searchWord(args.word)

        proverbAdapter = SearchDetailProverbAdapter {
            viewModel.searchWord(it.madde)
            addCounter()
        }

        alphabetAdapter = SearchDetailAlphabetAdapter()
        dataBinding.recycSearchAlphabet.adapter = alphabetAdapter
    }

    private fun addCounter(){
        val addCounter = preferenceStorage.searchCount
        preferenceStorage.searchCount = addCounter + 1
    }

    private fun observeSearch(){
        viewModel.searchResult.observe(this, EventObserver {
            if (it.status == Status.SUCCESS){

                val content = it.data?.get(0)

                dataBinding.searchContent = content

                val meanList = content!!.anlamlarListe
                val proverbList = content.atasozu
                val alphabetList = alphabetPerCharacter(content.madde)

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

        viewModel.getFavoriteByWord(args.word).observe(viewLifecycleOwner, Observer {
            dataBinding.favorite = it
            dataBinding.executePendingBindings()
        })
    }
}
