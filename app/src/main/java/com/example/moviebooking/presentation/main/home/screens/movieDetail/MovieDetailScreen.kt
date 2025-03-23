package com.example.moviebooking.presentation.main.home.screens.movieDetail

import android.os.Bundle
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.message
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.positiveAction
import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.domain.utils.simpleAlert
import com.example.baseproject.domain.utils.title
import com.example.baseproject.presentation.BaseFragment
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.movie.Movie
import com.example.moviebooking.databinding.FragmentMovieDetailScreenBinding
import com.example.moviebooking.domain.common.Const
import com.example.moviebooking.domain.viewmodels.MovieViewModel
import com.example.moviebooking.presentation.main.home.adapters.MovieDetailPagerAdapter
import com.google.android.material.tabs.TabLayout

class MovieDetailScreen :
    BaseFragment<FragmentMovieDetailScreenBinding, MovieDetailRouter, MainNavigator>(
        R.layout.fragment_movie_detail_screen
    ) {
    override val navigator: MainNavigator by navigatorViewModel()
    private val movieViewmodel: MovieViewModel by journeyViewModel()

    private lateinit var adapter: MovieDetailPagerAdapter

    override fun initView(savedInstanceState: Bundle?, binding: FragmentMovieDetailScreenBinding) {
        fetchData()
        initialize()
        observe()
        onClickListener()
    }

    private fun fetchData() {
        val movieID = arguments?.getString("movieID")
        movieViewmodel.fetchMovieDetail(movieID ?: "")
    }

    private fun initialize() {
        adapter = MovieDetailPagerAdapter(activity)
        with(binding) {
            pageTabs.apply {
                addOnTabSelectedListener(
                    object : TabLayout.OnTabSelectedListener {
                        override fun onTabSelected(tab: TabLayout.Tab?) {
                            when (tab?.position) {
                                0 -> detailViewPager.currentItem = 0
                                1 -> detailViewPager.currentItem = 1
                                2 -> detailViewPager.currentItem = 2
                            }
                        }

                        override fun onTabUnselected(tab: TabLayout.Tab?) {

                        }

                        override fun onTabReselected(tab: TabLayout.Tab?) {

                        }

                    }
                )
            }

            detailViewPager.apply {
                isUserInputEnabled = false
                offscreenPageLimit = 3
                adapter = this@MovieDetailScreen.adapter
            }
        }
    }

    private fun observe() {
        movieViewmodel.movieDetail.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> showLoading(isLoading = true)
                is ResponseStatus.Error -> {
                    showLoading(isLoading = false)
                    activity.simpleAlert {
                        title("Connection error!")
                        message("Something went wrong when fetching data. Please try again later :<")
                        positiveAction(name = "Back") { router?.onPopScreen() }
                    }
                }

                is ResponseStatus.Success -> {
                    showLoading(isLoading = false)
                    displayMovieDetail(status.data)
                }
            }
        }
    }

    private fun onClickListener() {
        with(binding) {
            backBtn.safeClick { router?.onPopScreen() }
        }
    }

    private fun displayMovieDetail(movie: Movie) {
        with(binding) {
            poster.loadImage(
                source = Const.tmdbImageUrlW500 + movie.posterPath,
                defaultImage = R.drawable.img_default_placeholder
            )
            movie.title?.let { title.text = it }
            durationReleaseDate.text = "${movie.durationFormat()}m • ${movie.releaseDate}"
            voteAverage.text = movie.voteAverage.toString() + " (${movie.voteCount})"
        }
    }
}