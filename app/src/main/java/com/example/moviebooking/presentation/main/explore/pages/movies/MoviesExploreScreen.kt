package com.example.moviebooking.presentation.main.explore.pages.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.baseproject.domain.utils.EndlessOnScrollListener
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentMoviesExploreBinding
import com.example.moviebooking.domain.viewmodels.MovieViewModel
import com.example.moviebooking.presentation.main.explore.ExploreRouter
import com.example.moviebooking.presentation.main.home.adapters.GenreAdapter
import com.example.moviebooking.presentation.main.home.adapters.MovieExploreAdapter
import com.example.moviebooking.presentation.main.home.screens.movieDetail.MovieDetailRouter

class MoviesExploreScreen : BaseFragment<FragmentMoviesExploreBinding, MovieExploreRouter, MainNavigator>(
    R.layout.fragment_movies_explore
) {
    override val navigator: MainNavigator by journeyViewModel()
    override val viewModel by journeyViewModel<MovieViewModel>()

    private lateinit var genreAdapter: GenreAdapter
    private lateinit var movieExploreAdapter: MovieExploreAdapter

    private var currentGenre: String? = null

    override fun initView(savedInstanceState: Bundle?, binding: FragmentMoviesExploreBinding) {
        setup()
        observer()
    }

    private fun setup() {
        genreAdapter = GenreAdapter {
            viewModel.discoverMovies(withGenres = it.id)
            currentGenre = it.id
        }

        movieExploreAdapter = MovieExploreAdapter {
            navigator.goToMovieDetailFromExplore(
                Bundle().apply {
                    putString("movieID", it.id.toString())
                }
            )
        }

        binding.movieCategoryRV.apply {
            adapter = genreAdapter
            setHasFixedSize(true)
            setItemViewCacheSize(20)
        }

        binding.movieRV.apply {
            adapter = movieExploreAdapter
            setItemViewCacheSize(20)
            setHasFixedSize(true)
            addOnScrollListener(object : EndlessOnScrollListener(layoutManager as GridLayoutManager) {
                override fun loadMoreItems(page: Int, totalItemsCount: Int) {
                    viewModel.discoverMovies(
                        page = page,
                        withGenres = currentGenre
                    )
                }

            })
        }

        viewModel.discoverMovies(genreAdapter.currentList.firstOrNull()?.id)
    }

    private fun observer() {
        viewModel.genreList.observe(viewLifecycleOwner) { status ->
            when(status) {
                is ResponseStatus.Loading -> {}
                is ResponseStatus.Error -> {}
                is ResponseStatus.Success -> {
                    genreAdapter.submitList(status.data.genres)
                    genreAdapter.setSelectedPosition(0)
                }
            }
        }

        viewModel.discoverList.observe(viewLifecycleOwner) { status ->
            when(status) {
                is ResponseStatus.Loading -> {}
                is ResponseStatus.Error -> {}
                is ResponseStatus.Success -> {
                    movieExploreAdapter.submitList(status.data.results)
                }
            }
        }
    }
}