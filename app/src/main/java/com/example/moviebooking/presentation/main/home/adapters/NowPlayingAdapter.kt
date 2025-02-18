package com.example.moviebooking.presentation.main.home.adapters

import coil.load
import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieItem
import com.example.moviebooking.databinding.NowPlayingCarouselItemBinding
import com.example.moviebooking.domain.common.Const

class NowPlayingAdapter: BaseListAdapter<MovieItem, NowPlayingCarouselItemBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.now_playing_carousel_item

    override fun bindView(holder: VH, binding: NowPlayingCarouselItemBinding, position: Int) {
        val item = getItem(position)

        with(binding) {

            item.backdropPath?.let { backdropPath ->
                backdrop.loadImage(
                    source = Const.tmdbImageUrlW500 + backdropPath,
                    defaultImage = R.drawable.img_default_placeholder
                )
            }

            item.originalTitle?.takeIf { it.isNotEmpty() }?.let { originalTitle.text = it }

            if(item.voteAverage != null && item.voteCount != null) {
                voteAverage.text = item.voteAverage.toString() + " (${item.voteCount})"
            }

            executePendingBindings()
        }
    }
}