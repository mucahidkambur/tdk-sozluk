package com.mucahitkambur.tdksozluk.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mucahitkambur.tdksozluk.databinding.ItemLayoutProverbBinding
import com.mucahitkambur.tdksozluk.model.search.Atasozu

class SearchDetailProverbAdapter (
    private val proverbList: List<Atasozu>
): RecyclerView.Adapter<SearchDetailProverbViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchDetailProverbViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchDetailProverbViewHolder(ItemLayoutProverbBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return proverbList.size
    }

    override fun onBindViewHolder(holder: SearchDetailProverbViewHolder, position: Int) {
        holder.bind(proverbList[position])
    }

}

class SearchDetailProverbViewHolder(
    val binding: ItemLayoutProverbBinding
): RecyclerView.ViewHolder(binding.root){

    fun bind(proverb: Atasozu){
        binding.proverb = proverb
    }
}