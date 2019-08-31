package com.mucahitkambur.tdksozluk.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mucahitkambur.tdksozluk.databinding.ItemLayoutAlphabetBinding

class SearchDetailAlphabetAdapter (
    private val alphabetList: List<String>
): RecyclerView.Adapter<SearchDetailAlphabetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchDetailAlphabetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchDetailAlphabetViewHolder(ItemLayoutAlphabetBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return alphabetList.size
    }

    override fun onBindViewHolder(holder: SearchDetailAlphabetViewHolder, position: Int) {
        holder.bind(alphabetList[position])
    }

}

class SearchDetailAlphabetViewHolder(
    val binding: ItemLayoutAlphabetBinding
): RecyclerView.ViewHolder(binding.root){

    fun bind(alphabet: String){
        binding.alphabet = alphabet
    }
}