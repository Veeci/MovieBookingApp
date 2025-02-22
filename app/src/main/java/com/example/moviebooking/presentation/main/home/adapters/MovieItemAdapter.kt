package com.example.moviebooking.presentation.main.home.adapters

import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieItem
import com.example.moviebooking.databinding.MovieItemBinding
import com.example.moviebooking.domain.common.Const

class MovieItemAdapter: BaseListAdapter<MovieItem, MovieItemBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.movie_item

    override fun bindView(holder: VH, binding: MovieItemBinding, position: Int) {
        val item = getItem(position)

        with(binding) {
            poster.loadImage(
                source = Const.tmdbImageUrlW500 + item.posterPath,
                defaultImage = R.drawable.img_default_placeholder
            )

            item.title.takeIf { !it.isNullOrEmpty() }?.let { title.text = item.title?.uppercase() }

            if(item.voteAverage != null && item.voteCount != null) {
                voteAverage.text = item.voteAverage.toString() + " (${item.voteCount})"
            }
        }
    }
}