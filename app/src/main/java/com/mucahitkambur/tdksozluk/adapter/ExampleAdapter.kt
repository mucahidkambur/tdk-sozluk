package com.mucahitkambur.tdksozluk.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mucahitkambur.tdksozluk.databinding.ItemLayoutExampleBinding
import com.mucahitkambur.tdksozluk.model.search.AnlamlarListe
import com.mucahitkambur.tdksozluk.model.search.OrneklerListe

class ExampleAdapter (
    private val exampleList: List<OrneklerListe>
): RecyclerView.Adapter<ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ExampleViewHolder(ItemLayoutExampleBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return exampleList.size
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        holder.bind(exampleList[position])
    }

}

class ExampleViewHolder(
    val binding: ItemLayoutExampleBinding
): RecyclerView.ViewHolder(binding.root){

    fun bind(example: OrneklerListe){

        binding.example = example
        binding.executePendingBindings()
    }
}