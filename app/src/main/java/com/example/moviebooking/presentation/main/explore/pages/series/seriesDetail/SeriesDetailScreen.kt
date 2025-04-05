package com.example.moviebooking.presentation.main.explore.pages.series.seriesDetail

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.Genre
import com.example.moviebooking.data.remote.entities.tmdb.series.EpisodesItem
import com.example.moviebooking.data.remote.entities.tmdb.series.SerieSeasonDTO
import com.example.moviebooking.data.remote.entities.tmdb.series.Series
import com.example.moviebooking.databinding.FragmentSeriesDetailBinding
import com.example.moviebooking.domain.common.BoundsOffsetDecoration
import com.example.moviebooking.domain.common.Const
import com.example.moviebooking.domain.common.ProminentLayoutManager
import com.example.moviebooking.domain.viewmodels.SeriesViewModel
import com.example.moviebooking.presentation.main.home.adapters.GenreTagAdapter
import com.example.moviebooking.presentation.main.home.adapters.ImageAdapter
import com.example.moviebooking.presentation.main.home.adapters.RecommendedSeriesAdapter
import com.example.moviebooking.presentation.main.home.adapters.SeasonAdapter
import com.example.moviebooking.presentation.main.home.adapters.SimilarSeriesAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class SeriesDetailScreen :
    BaseFragment<FragmentSeriesDetailBinding, SeriesDetailRouter, MainNavigator>(
        R.layout.fragment_series_detail
    ) {
    override val navigator: MainNavigator by journeyViewModel()
    override val viewModel by journeyViewModel<SeriesViewModel>()

    private var seriesID: String = ""
    private var bottomSheet = SeriesBottomSheet()

    private var genreList = listOf<Genre>()
    private var genreTagAdapter = GenreTagAdapter()
    private lateinit var seasonAdapter: SeasonAdapter
    private var imageAdapter = ImageAdapter()
    private var similarSeriesAdapter = SimilarSeriesAdapter()
    private var recommendedSeriesAdapter = RecommendedSeriesAdapter()
    private var videoId: String = ""

    private val seasonEpisodesMap = mutableMapOf<String, List<EpisodesItem>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        seriesID = arguments?.getString("seriesID") ?: ""
        val previousSheet =
            parentFragmentManager.findFragmentByTag(SeriesBottomSheet::class.java.name)
        if (previousSheet is SeriesBottomSheet) {
            previousSheet.dismissAllowingStateLoss()
        }
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSeriesDetailBinding) {
        fetchData()
        observer()
        onClickListener()
    }

    private fun fetchData() {
        viewModel.fetchSeriesDetail(seriesID)
        viewModel.fetchSeriesImages(seriesID)
        viewModel.fetchSeriesVideo(seriesID)
        viewModel.fetchSimilarSeries(seriesID)
        viewModel.fetchRecommendationSeries(seriesID)
    }

    private fun observer() {
        viewModel.seriesDetail.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> {
                    showLoading(isLoading = true, preventClicking = true)
                }

                is ResponseStatus.Error -> {
                    showLoading(isLoading = false)
                }

                is ResponseStatus.Success -> {
                    showLoading(isLoading = false)
                    displaySeriesInfo(status.data)

                    if (bottomSheet.isAdded) {
                        bottomSheet.dismissAllowingStateLoss()
                    }

                    bottomSheet = SeriesBottomSheet(status.data)
                    bottomSheet.show(parentFragmentManager, SeriesBottomSheet::class.java.name)

                    for (season in status.data.seasons ?: emptyList()) {
                        viewModel.fetchSeriesSeasonDetail(
                            serieId = status.data.id ?: "",
                            seasonNumber = season?.seasonNumber?.toString() ?: ""
                        )
                    }
                }
            }
        }

        viewModel.seriesGenreList.observe(viewLifecycleOwner) { status ->
            if (status is ResponseStatus.Success) {
                genreTagAdapter.submitList(status.data.genres)
                genreList = status.data.genres ?: emptyList()
            }
        }

        viewModel.seriesImages.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> {
                    showLoading(isLoading = true, preventClicking = true)
                }

                is ResponseStatus.Error -> {
                    showLoading(isLoading = false)
                }

                is ResponseStatus.Success -> {
                    showLoading(isLoading = false)
                    imageAdapter.submitList(status.data.posters)
                }
            }
        }

        viewModel.seriesVideo.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> {
                    showLoading(isLoading = true, preventClicking = true)
                }

                is ResponseStatus.Error -> {
                    showLoading(isLoading = false)
                }

                is ResponseStatus.Success -> {
                    showLoading(isLoading = false)
                    videoId = status.data.results?.random()?.key.toString()
                }
            }
        }

        viewModel.similarSeries.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> {
                    showLoading(isLoading = true, preventClicking = true)
                }

                is ResponseStatus.Error -> {
                    showLoading(isLoading = false)
                }

                is ResponseStatus.Success -> {
                    showLoading(isLoading = false)
                    similarSeriesAdapter.submitList(status.data.results)
                }
            }
        }

        viewModel.recommendedSeries.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> {
                    showLoading(isLoading = true, preventClicking = true)
                }

                is ResponseStatus.Error -> {
                    showLoading(isLoading = false)
                }

                is ResponseStatus.Success -> {
                    showLoading(isLoading = false)
                    recommendedSeriesAdapter.submitList(status.data.results)
                }
            }
        }

        viewModel.seriesSeasonDetail.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> {
                    showLoading(isLoading = true, preventClicking = true)
                }
                is ResponseStatus.Error -> {
                    showLoading(isLoading = false)
                }
                is ResponseStatus.Success -> {
                    showLoading(isLoading = false)
                    for (episode in status.data.episodes ?: emptyList()) {
                        seasonEpisodesMap[status.data.seasonNumber.toString()] =
                            status.data.episodes ?: emptyList()
                    }
                }
            }
        }
    }

    private fun displaySeriesInfo(series: Series) {
        with(binding) {
            backdropPathIV.apply {
                loadImage(
                    source = Const.tmdbImageUrlOriginal + series.backdropPath,
                    defaultImage = R.drawable.img_default_placeholder
                )

                val list = genreList.filter { genre ->
                    series.genres?.any { it?.id.toString() == genre.id } == true
                }

                genreListRV.apply {
                    adapter = genreTagAdapter
                    genreTagAdapter.submitList(list)
                }

                nameTV.text = series.name
                numberOfSeasonTV.text =
                    context.getString(R.string.number_of_seasons, series.numberOfSeasons)
                numberOfEpisodesTV.text =
                    context.getString(R.string.number_of_episodes, series.numberOfEpisodes)
                typeTV.text = context.getString(R.string.type, series.type)
                rating.text =
                    context.getString(R.string.rating, series.voteAverage, series.voteCount)

                seasonAdapter = SeasonAdapter(
                    onMoreSlicked = { item ->
                        seasonEpisodesMap[item.seasonNumber.toString()] ?: emptyList()
                    }
                )

                binding.seasonRV.apply {
                    adapter = seasonAdapter
                    seasonAdapter.submitList(series.seasons)
                }

                imageRV.apply {
                    adapter = imageAdapter
                    layoutManager =
                        ProminentLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    PagerSnapHelper().attachToRecyclerView(this)
                    addItemDecoration(BoundsOffsetDecoration())
                }

                similarRV.apply {
                    adapter = similarSeriesAdapter
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    setHasFixedSize(true)
                    setItemViewCacheSize(20)
                }

                recommendedRV.apply {
                    adapter = recommendedSeriesAdapter
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    setHasFixedSize(true)
                    setItemViewCacheSize(20)
                }

                lifecycle.addObserver(trailerPlayer)
                trailerPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        videoId.takeIf { it.isNotBlank() }?.let {
                            youTubePlayer.loadVideo(it, 0f)
                        }
                    }
                })
            }
        }
    }

    private fun onClickListener() {
        with(binding) {
            btnBack.setOnClickListener { navigator.onPopScreen() }
        }
    }

    override fun onPause() {
        super.onPause()

        binding.trailerPlayer.release()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding.trailerPlayer.release()
    }
}