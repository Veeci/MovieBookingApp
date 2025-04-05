package com.example.moviebooking.presentation.main.home.adapters

import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.Genre
import com.example.moviebooking.databinding.GenreTagItemBinding

class GenreTagAdapter : BaseListAdapter<Genre, GenreTagItemBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.genre_tag_item

    override fun bindView(holder: VH, binding: GenreTagItemBinding, position: Int) {
        val item = getItem(position)

        binding.genreTV.text = item.name
    }
}