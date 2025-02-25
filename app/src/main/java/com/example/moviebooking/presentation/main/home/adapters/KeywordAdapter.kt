package com.example.moviebooking.presentation.main.home.adapters

import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.movie.KeywordsItem
import com.example.moviebooking.databinding.KeywordItemBinding

class KeywordAdapter : BaseListAdapter<KeywordsItem, KeywordItemBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.keyword_item

    override fun bindView(holder: VH, binding: KeywordItemBinding, position: Int) {
        val item = getItem(position)

        item.name?.let { binding.name.text = "#${it}" }
    }
}