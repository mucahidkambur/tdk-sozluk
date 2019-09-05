package com.mucahitkambur.tdksozluk.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.mucahitkambur.tdksozluk.databinding.ItemSearchBinding
import com.mucahitkambur.tdksozluk.model.search.Suggestion
import java.util.*

class SearchAdapter (
    private val suggestionClick: (suggestion: Suggestion) -> Unit
): RecyclerView.Adapter<SearchViewHolder>() {

    private var suggestionsFiltered: List<Suggestion> = arrayListOf()
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchViewHolder(ItemSearchBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
       return suggestionsFiltered.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(suggestionsFiltered[position], suggestionClick)
    }

    fun setSuggestions(sugggestionList: List<Suggestion>?){
        sugggestionList?.let {
            suggestionsFiltered = sugggestionList
            notifyDataSetChanged()
        }
    }
}

class SearchViewHolder(
    val binding: ItemSearchBinding
): RecyclerView.ViewHolder(binding.root){

    fun bind(suggestion: Suggestion,
             suggestioClick: ((suggestion: Suggestion) -> Unit)){
        binding.root.setOnClickListener { suggestioClick.invoke(suggestion) }
        binding.suggestion = suggestion.madde
        binding.executePendingBindings()
    }
}