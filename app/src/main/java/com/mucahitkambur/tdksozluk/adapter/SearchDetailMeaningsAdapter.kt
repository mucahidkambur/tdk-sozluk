package com.mucahitkambur.tdksozluk.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mucahitkambur.tdksozluk.databinding.ItemLayoutMeanBinding
import com.mucahitkambur.tdksozluk.model.search.AnlamlarListe

class SearchDetailMeaningsAdapter (
    private val anlamlarListe: List<AnlamlarListe>
): RecyclerView.Adapter<SearchDetailViewHolder>() {

    var type: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchDetailViewHolder(ItemLayoutMeanBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return anlamlarListe.size
    }

    override fun onBindViewHolder(holder: SearchDetailViewHolder, position: Int) {
        holder.bind(anlamlarListe[position])
    }

}

class SearchDetailViewHolder(
    val binding: ItemLayoutMeanBinding
): RecyclerView.ViewHolder(binding.root){

    fun bind(mean: AnlamlarListe){

        binding.mean = mean
        if (!mean.orneklerListe.isNullOrEmpty())
            binding.recycExample.adapter = ExampleAdapter(mean.orneklerListe)
        binding.executePendingBindings()
    }
}