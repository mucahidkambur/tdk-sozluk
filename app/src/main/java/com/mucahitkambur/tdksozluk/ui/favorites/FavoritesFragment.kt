package com.mucahitkambur.tdksozluk.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mucahitkambur.tdksozluk.R
import com.mucahitkambur.tdksozluk.adapter.FavoritesAdapter
import com.mucahitkambur.tdksozluk.databinding.FragmentFavoritesBinding
import com.mucahitkambur.tdksozluk.databinding.FragmentMainBinding
import com.mucahitkambur.tdksozluk.di.Injectable
import com.mucahitkambur.tdksozluk.util.divider
import com.mucahitkambur.tdksozluk.util.findNavController
import com.mucahitkambur.tdksozluk.util.startSearchDetail
import com.mucahitkambur.tdksozluk.util.viewModelProvider
import javax.inject.Inject

class FavoritesFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: FavoritesViewModel

    private lateinit var dataBinding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = viewModelProvider(viewModelFactory)
        dataBinding = DataBindingUtil.inflate<FragmentFavoritesBinding>(
            inflater,
            R.layout.fragment_favorites,
            container,
            false
        ).apply {
            lifecycleOwner = this@FavoritesFragment
            viewModel = this@FavoritesFragment.viewModel
        }
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFavorites()
    }

    private fun observeFavorites() {
        viewModel.getFavorites().observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                dataBinding.clEmptyView.visibility = View.VISIBLE
            } else {
                dataBinding.clEmptyView.visibility = View.GONE
            }
            val adapter = FavoritesAdapter()
            adapter.favoriteClicked = {
                findNavController().navigate(
                    FavoritesFragmentDirections.actionNavFavoritesToNavSearchDetail(
                        it.word
                    )
                )
            }
            dataBinding.recycFavorites.adapter = adapter
            dataBinding.recycFavorites.addItemDecoration(divider())
            adapter.items = it
        })
    }

}