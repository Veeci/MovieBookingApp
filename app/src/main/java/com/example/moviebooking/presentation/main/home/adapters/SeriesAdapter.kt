package com.example.moviebooking.presentation.main.home.adapters

import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.series.SeriesItem
import com.example.moviebooking.databinding.SeriesItemBinding
import com.example.moviebooking.domain.common.Const

class SeriesAdapter(
    private val genreList: (SeriesItem) -> String,
    private val onItemClick: (SeriesItem) -> Unit
) : BaseListAdapter<SeriesItem, SeriesItemBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.series_item

    override fun bindView(holder: VH, binding: SeriesItemBinding, position: Int) {
        val item = getItem(position)

        with(binding) {
            nameTV.text = item.name
            voteAverage.text = item.voteAverage.toString()
            genreTV.text = genreList(item)
            overview.text = item.overview
            backdropPathIV.loadImage(
                source = Const.tmdbImageUrlOriginal + item.backdropPath,
                defaultImage = R.drawable.ic_movies
            )

            root.safeClick { onItemClick(item) }
        }
    }
}