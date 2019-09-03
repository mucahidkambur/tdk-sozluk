package com.mucahitkambur.tdksozluk.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mucahitkambur.tdksozluk.databinding.ItemLayoutProverbBinding
import com.mucahitkambur.tdksozluk.model.search.Atasozu

class SearchDetailProverbAdapter (private val proverbClick: (proverb: Atasozu) -> Unit)
    : RecyclerView.Adapter<SearchDetailProverbViewHolder>() {

    private var proverbList: MutableList<Atasozu> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchDetailProverbViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchDetailProverbViewHolder(ItemLayoutProverbBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return proverbList.size
    }

    override fun onBindViewHolder(holder: SearchDetailProverbViewHolder, position: Int) {
        holder.bind(proverbList[position], proverbClick)
    }

    fun setProverbs(proverbs: List<Atasozu>?){

        proverbs?.let {
            proverbList = proverbs.toMutableList()
            notifyDataSetChanged()
        }
    }

}

class SearchDetailProverbViewHolder(
    val binding: ItemLayoutProverbBinding
): RecyclerView.ViewHolder(binding.root){

    fun bind(proverb: Atasozu,
             proverbClick: (proverb: Atasozu) -> Unit){
        binding.proverb = proverb
        binding.cardViewProverb.setOnClickListener {
            proverbClick.invoke(proverb)
        }

    }
}