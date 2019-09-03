package com.mucahitkambur.tdksozluk.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mucahitkambur.tdksozluk.databinding.ItemLayoutAlphabetBinding

class SearchDetailAlphabetAdapter : RecyclerView.Adapter<SearchDetailAlphabetViewHolder>() {

    private var alphabetList: MutableList<String> = arrayListOf()

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

    fun setAlphabets(alphabets: List<String>?){
        alphabets?.let {
            alphabetList = alphabets.toMutableList()
            notifyDataSetChanged()
        }
    }

}

class SearchDetailAlphabetViewHolder(
    val binding: ItemLayoutAlphabetBinding
): RecyclerView.ViewHolder(binding.root){

    fun bind(alphabet: String){
        binding.alphabet = alphabet
    }
}