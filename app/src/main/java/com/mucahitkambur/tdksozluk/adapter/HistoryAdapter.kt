package com.mucahitkambur.tdksozluk.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mucahitkambur.tdksozluk.databinding.ItemLayoutHistoryBinding
import com.mucahitkambur.tdksozluk.model.search.History

class HistoryAdapter (
    private val historyList: List<History>,
    private val historyClick: (history: History) -> Unit
): RecyclerView.Adapter<SearchHistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchHistoryViewHolder(ItemLayoutHistoryBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        holder.bind(historyList[position], historyClick)
    }

}

class SearchHistoryViewHolder(
    val binding: ItemLayoutHistoryBinding
): RecyclerView.ViewHolder(binding.root){

    fun bind(history: History,
             historyClick: (history: History) -> Unit){
        binding.history = history.word
        binding.relativeHistory.setOnClickListener { historyClick.invoke(history) }
    }
}