package com.mucahitkambur.tdksozluk.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mucahitkambur.tdksozluk.databinding.ItemLayoutHistoryBinding
import com.mucahitkambur.tdksozluk.model.search.History

class HistoryAdapter (
    private val historyList: List<History>
): RecyclerView.Adapter<SearchHistoryViewHolder>() {

    var historyClick: ((History) -> Unit)? = null
    var deleteClick: ((History) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchHistoryViewHolder(ItemLayoutHistoryBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        holder.bind(historyList[position], historyClick, deleteClick)
    }

}

class SearchHistoryViewHolder(val binding: ItemLayoutHistoryBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(
        history: History,
        historyClick: ((history: History) -> Unit)?,
        deleteClick: ((history: History) -> Unit)?
    ){
        binding.history = history.word
        binding.root.setOnClickListener { historyClick?.invoke(history) }
        binding.imgDelete.setOnClickListener { deleteClick?.invoke(history) }
        binding.executePendingBindings()
    }
}