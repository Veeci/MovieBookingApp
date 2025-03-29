package com.example.moviebooking.presentation.main.home.adapters

import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieItem
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieList
import com.example.moviebooking.databinding.MovieExploreItemBinding
import com.example.moviebooking.domain.common.Const

class MovieExploreAdapter(
    private val onItemClicked: (MovieItem) -> Unit
) : BaseListAdapter<MovieItem, MovieExploreItemBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.movie_explore_item

    override fun bindView(holder: VH, binding: MovieExploreItemBinding, position: Int) {
        val item = getItem(position)

        with(binding) {
            tittleTV.text = item.title
            voteAverageTV.text = item.voteAverage.toString()
            backdropPathIV.loadImage(
                source = Const.tmdbImageUrlOriginal + item.backdropPath,
                defaultImage = R.drawable.ic_movies
            )

            binding.root.safeClick { onItemClicked(item) }
        }
    }
}