package com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.similar

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentSimilarBinding
import com.example.moviebooking.domain.common.BoundsOffsetDecoration
import com.example.moviebooking.domain.common.ProminentLayoutManager
import com.example.moviebooking.domain.viewmodels.MovieViewModel
import com.example.moviebooking.presentation.main.home.adapters.ImageAdapter
import com.example.moviebooking.presentation.main.home.adapters.RecommendedMMovieAdapter
import com.example.moviebooking.presentation.main.home.adapters.ReviewAdapter
import com.example.moviebooking.presentation.main.home.adapters.SimilarMovieAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

@RequiresApi(Build.VERSION_CODES.O)
class SimilarFragment : BaseFragment<FragmentSimilarBinding, SimilarRouter, MainNavigator>(
    R.layout.fragment_similar
) {
    override val navigator: MainNavigator by navigatorViewModel()
    private val movieViewmodel: MovieViewModel by journeyViewModel()

    private lateinit var imageAdapter: ImageAdapter
    private lateinit var similarMovieAdapter: SimilarMovieAdapter
    private lateinit var recommendedMovieAdapter: RecommendedMMovieAdapter
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var videoId: String

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSimilarBinding) {
        setup()
        fetchData()
        observe()
    }

    private fun setup() {
        with(binding) {
            imageAdapter = ImageAdapter().apply {

            }

            similarMovieAdapter = SimilarMovieAdapter().apply {

            }

            recommendedMovieAdapter = RecommendedMMovieAdapter().apply {

            }

            reviewAdapter = ReviewAdapter().apply {

            }

            imageRV.apply {
                adapter = imageAdapter
                layoutManager = ProminentLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                PagerSnapHelper().attachToRecyclerView(this)
                addItemDecoration(BoundsOffsetDecoration())
            }

            similarRV.apply {
                adapter = similarMovieAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                setItemViewCacheSize(20)
            }

            recommendedRV.apply {
                adapter = recommendedMovieAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                setItemViewCacheSize(20)
            }

            reviewRV.apply {
                adapter = reviewAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                setItemViewCacheSize(20)
            }

            lifecycle.addObserver(trailerPlayer)
            trailerPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })
        }
    }

    private fun fetchData() {
        movieViewmodel.fetchImage(movieViewmodel.movieId.value.toString())
        movieViewmodel.fetchVideo(movieViewmodel.movieId.value.toString())
        movieViewmodel.fetchRecommendedMovies(movieViewmodel.movieId.value.toString())
        movieViewmodel.fetchSimilarMovies(movieViewmodel.movieId.value.toString())
        movieViewmodel.fetchReviews(movieViewmodel.movieId.value.toString())
    }

    private fun observe() {
        movieViewmodel.imageList.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> showLoading(isLoading = true, preventClicking = true)
                is ResponseStatus.Error -> {
                    showLoading(isLoading = false, preventClicking = true)

                }

                is ResponseStatus.Success -> {
                    showLoading(isLoading = false, preventClicking = false)
                    imageAdapter.submitList(status.data.posters)
                }
            }
        }

        movieViewmodel.videoList.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> showLoading(isLoading = true, preventClicking = true)
                is ResponseStatus.Error -> {
                    showLoading(isLoading = false, preventClicking = false)

                }

                is ResponseStatus.Success -> {
                    showLoading(isLoading = false, preventClicking = false)
                    videoId = status.data.results?.random()?.key.toString()
                }
            }
        }

        movieViewmodel.similarMovies.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> showLoading(isLoading = true, preventClicking = true)
                is ResponseStatus.Error -> {
                    showLoading(isLoading = false, preventClicking = true)

                }

                is ResponseStatus.Success -> {
                    showLoading(isLoading = false, preventClicking = false)
                    similarMovieAdapter.submitList(status.data.results)
                }
            }
        }

        movieViewmodel.recommendedMovies.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> showLoading(isLoading = true, preventClicking = true)
                is ResponseStatus.Error -> {
                    showLoading(isLoading = false, preventClicking = true)

                }

                is ResponseStatus.Success -> {
                    showLoading(isLoading = false, preventClicking = false)
                    recommendedMovieAdapter.submitList(status.data.results)
                }
            }
        }

        movieViewmodel.reviews.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> showLoading(isLoading = true, preventClicking = true)
                is ResponseStatus.Error -> {
                    showLoading(isLoading = false, preventClicking = true)

                }

                is ResponseStatus.Success -> {
                    showLoading(isLoading = false, preventClicking = false)
                    reviewAdapter.submitList(status.data.results)
                }
            }
        }
    }
}