package com.example.moviebooking.presentation.main.home.adapters

import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieItem
import com.example.moviebooking.databinding.UpcomingItemBinding
import com.example.moviebooking.domain.common.Const

class UpcomingAdapter: BaseListAdapter<MovieItem, UpcomingItemBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.upcoming_item

    override fun bindView(holder: VH, binding: UpcomingItemBinding, position: Int) {
        val item = getItem(position)

        with(binding) {
            poster.loadImage(
                source = Const.tmdbImageUrlW500 + item.posterPath,
                defaultImage = R.drawable.img_default_placeholder
            )

            item.title?.takeIf { it.isNotEmpty() }?.let{ title.text = it }

            item.releaseDate?.takeIf { it.isNotEmpty() }?.let { releaseDate.text = it }

            executePendingBindings()
        }
    }
}