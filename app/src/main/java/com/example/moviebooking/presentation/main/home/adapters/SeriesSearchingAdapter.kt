package com.example.moviebooking.presentation.main.home.adapters

import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.series.SeriesItem
import com.example.moviebooking.databinding.NowPlayingCarouselItemBinding
import com.example.moviebooking.domain.common.Const

class SeriesSearchingAdapter(
    private val onItemClick: (SeriesItem) -> Unit
) : BaseListAdapter<SeriesItem, NowPlayingCarouselItemBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.now_playing_carousel_item

    override fun bindView(holder: VH, binding: NowPlayingCarouselItemBinding, position: Int) {
        val item = getItem(position)

        with(binding) {
            backdrop.loadImage(
                source = Const.tmdbImageUrlOriginal + item.backdropPath,
                defaultImage = R.drawable.img_default_placeholder
            )

            originalTitle.text = item.name
            voteAverage.text = item.voteAverage.toString() + " (${item.voteCount})"

            root.safeClick { onItemClick(item) }
        }
    }
}