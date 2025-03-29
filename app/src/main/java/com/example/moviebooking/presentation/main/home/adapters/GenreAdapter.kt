package com.example.moviebooking.presentation.main.home.adapters

import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.Genre
import com.example.moviebooking.databinding.GenreItemBinding

class GenreAdapter(
    private val onItemClick: (Genre) -> Unit
) : BaseListAdapter<Genre, GenreItemBinding>() {
    private var selectedPosition = -1

    override fun getLayoutId(viewType: Int) = R.layout.genre_item

    override fun bindView(holder: VH, binding: GenreItemBinding, position: Int) {
        val item = getItem(position)

        binding.genreItem.text = item.name
        binding.genreItem.isSelected = (position == selectedPosition)

        binding.root.safeClick {
            val previousPosition = selectedPosition
            selectedPosition = position
            if(previousPosition != -1) notifyItemChanged(previousPosition)
            notifyItemChanged(position)
            onItemClick(item)
        }
    }

    fun setSelectedPosition(position: Int) {
        val previousPosition = selectedPosition
        selectedPosition = position
        if(previousPosition != -1) notifyItemChanged(previousPosition)
        notifyItemChanged(position)
    }
}