package com.example.moviebooking.presentation.main.home.adapters

import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.series.RecommendationSerieItem
import com.example.moviebooking.databinding.SimilarMovieItemBinding
import com.example.moviebooking.domain.common.Const

class RecommendedSeriesAdapter : BaseListAdapter<RecommendationSerieItem, SimilarMovieItemBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.similar_movie_item

    override fun bindView(holder: VH, binding: SimilarMovieItemBinding, position: Int) {
        val item = getItem(position)

        with(binding) {
            poster.loadImage(
                source = Const.tmdbImageUrlW500 + item.posterPath,
                defaultImage = R.drawable.img_default_placeholder
            )

            title.text = item.name
            voteAverage.text = item.voteAverage.toString() + " (${item.voteCount})"
            genreTV.text = item.overview
        }
    }
}