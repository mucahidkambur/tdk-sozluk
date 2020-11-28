package com.mucahitkambur.tdksozluk.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mucahitkambur.tdksozluk.databinding.ItemLayoutFavoriteBinding
import com.mucahitkambur.tdksozluk.databinding.ItemSearchBinding
import com.mucahitkambur.tdksozluk.model.favorites.Favorite

class FavoritesAdapter : RecyclerView.Adapter<FavoritesViewHolder>() {

    var favoriteClicked: ((Favorite) -> Unit)? = null

    var items: List<Favorite> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavoritesViewHolder(ItemLayoutFavoriteBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(items[position], favoriteClicked)
    }

}

class FavoritesViewHolder(val binding: ItemLayoutFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(favorite: Favorite, favoriteClicked: ((favorite: Favorite) -> Unit)?) {
        binding.root.setOnClickListener { favoriteClicked?.invoke(favorite) }
        binding.favorite = favorite.word
        binding.executePendingBindings()
    }
}