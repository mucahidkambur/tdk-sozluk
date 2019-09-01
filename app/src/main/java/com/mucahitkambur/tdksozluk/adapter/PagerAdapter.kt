package com.mucahitkambur.tdksozluk.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.mucahitkambur.tdksozluk.databinding.ItemPagerBinding
import com.mucahitkambur.tdksozluk.model.main.Karistirma

class PageAdapter(private val mixed: List<Karistirma>,
                  private val mixedClick: (word: String) -> Unit): PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return mixed.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val binding = ItemPagerBinding.inflate(inflater, container, false)
        binding.mixed = mixed[position]
        binding.textFalse.setOnClickListener{mixedClick.invoke(mixed[position].yanlis)}
        binding.textTrue.setOnClickListener{mixedClick.invoke(mixed[position].dogru)}
        container.addView(binding.root)
        return binding.root
    }

    override fun getPageWidth(position: Int): Float {
        return 0.5f
    }

}