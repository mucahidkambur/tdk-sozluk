package com.mucahitkambur.tdksozluk.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.mucahitkambur.tdksozluk.databinding.ItemSearchBinding
import com.mucahitkambur.tdksozluk.model.Autocomplete
import java.util.*

class SearchAdapter (
    private val autocomplete: List<Autocomplete>,
    private val wordClick: (autocomplete: Autocomplete) -> Unit
): RecyclerView.Adapter<SearchViewHolder>(), Filterable {

    private var autocompleteFiltered: List<Autocomplete> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchViewHolder(ItemSearchBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
       return autocompleteFiltered.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(autocompleteFiltered[position], wordClick)
    }

    override fun getFilter(): Filter {
        return object: Filter(){
            override fun performFiltering(text: CharSequence?): FilterResults {
                val filteredList = mutableListOf<Autocomplete>()
                for (word in autocomplete){
                    if (word.madde.startsWith(text.toString().toLowerCase(Locale.getDefault()), true) &&
                            filteredList.size < 11){
                        filteredList.add(word)
                    }
                }

                autocompleteFiltered = filteredList

                val filterResult = FilterResults()
                filterResult.values = autocompleteFiltered
                return filterResult
            }

            override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                autocompleteFiltered = results?.values as List<Autocomplete>
                notifyDataSetChanged()
            }

        }
    }
}

class SearchViewHolder(
    val binding: ItemSearchBinding
): RecyclerView.ViewHolder(binding.root){

    fun bind(word: Autocomplete,
             wordClick: ((autocomplete: Autocomplete) -> Unit)){
        binding.root.setOnClickListener { wordClick.invoke(word) }
        binding.word = word.madde
        binding.executePendingBindings()
    }
}