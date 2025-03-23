package com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.about

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.message
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.positiveAction
import com.example.baseproject.domain.utils.simpleAlert
import com.example.baseproject.domain.utils.title
import com.example.baseproject.presentation.BaseFragment
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.movie.CastItem
import com.example.moviebooking.data.remote.entities.tmdb.movie.Movie
import com.example.moviebooking.databinding.FragmentAboutBinding
import com.example.moviebooking.domain.common.Const
import com.example.moviebooking.domain.viewmodels.MovieViewModel
import com.example.moviebooking.presentation.main.home.adapters.CastAdapter
import com.example.moviebooking.presentation.main.home.adapters.CrewAdapter
import com.example.moviebooking.presentation.main.home.adapters.KeywordAdapter
import com.example.moviebooking.presentation.main.home.adapters.ProductionAdapter

class AboutFragment : BaseFragment<FragmentAboutBinding, AboutRouter, MainNavigator>(
    R.layout.fragment_about
) {
    override val navigator: MainNavigator by navigatorViewModel()
    private val movieViewmodel: MovieViewModel by journeyViewModel()

    private lateinit var castAdapter: CastAdapter
    private lateinit var crewAdapter: CrewAdapter
    private lateinit var keywordAdapter: KeywordAdapter
    private lateinit var productionAdapter: ProductionAdapter

    override fun initView(savedInstanceState: Bundle?, binding: FragmentAboutBinding) {
        setup()
        fetchData()
        observe()
    }

    private fun setup() {
        castAdapter = CastAdapter().apply {

        }

        keywordAdapter = KeywordAdapter().apply {

        }

        crewAdapter = CrewAdapter().apply {

        }

        productionAdapter = ProductionAdapter().apply {

        }

        with(binding) {
            overview.apply {
                setShowingLine(4)
                addShowMoreText("Show more")
                addShowLessText("Show less")
                setShowMoreTextColor(R.color.ThemePrimary)
                setShowLessTextColor(R.color.ThemeTertiary)
            }

            castRV.apply {
                adapter = castAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setItemViewCacheSize(20)
                setHasFixedSize(true)
            }

            crewRV.apply {
                adapter = crewAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setItemViewCacheSize(20)
                setHasFixedSize(true)
            }

            keywordRV.apply {
                adapter = keywordAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setItemViewCacheSize(20)
                setHasFixedSize(true)
            }

            productionRV.apply {
                adapter = productionAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setItemViewCacheSize(20)
                setHasFixedSize(true)
            }
        }
    }

    private fun fetchData() {
        movieViewmodel.fetchCastList(movieViewmodel.movieId.value.toString())
        movieViewmodel.fetchKeywordList(movieViewmodel.movieId.value.toString())
    }

    private fun observe() {
        movieViewmodel.movieDetail.observe(viewLifecycleOwner) { status ->
            when(status) {
                is ResponseStatus.Loading -> showLoading(isLoading = true, preventClicking = true)
                is ResponseStatus.Error -> {
                    showLoading(isLoading = false, preventClicking = true)
                    activity.simpleAlert {
                        title("Connection error!")
                        message("Something went wrong when fetching data. Please try again later :<")
                        positiveAction(name = "Back") { navigator.onPopScreen() }
                    }
                }
                is ResponseStatus.Success -> {
                    showLoading(isLoading = false, preventClicking = false)
                    productionAdapter.submitList(status.data.productionCompanies)
                    displayMovieDetail(status.data)
                }
            }
        }

        movieViewmodel.castList.observe(viewLifecycleOwner) { status ->
            when(status) {
                is ResponseStatus.Loading -> showLoading(isLoading = true, preventClicking = true)
                is ResponseStatus.Error -> {
                    showLoading(isLoading = false, preventClicking = true)
                }
                is ResponseStatus.Success -> {
                    showLoading(isLoading = false)
                    castAdapter.submitList(status.data.cast)
                    crewAdapter.submitList(status.data.crew)
                }
            }
        }

        movieViewmodel.keywordList.observe(viewLifecycleOwner) { status ->
            when(status) {
                is ResponseStatus.Loading -> showLoading(isLoading = true, preventClicking = true)
                is ResponseStatus.Error -> {
                    showLoading(isLoading = false)
                }
                is ResponseStatus.Success -> {
                    showLoading(isLoading = false)
                    keywordAdapter.submitList(status.data.keywords)
                }
            }
        }
    }

    private fun displayMovieDetail(data: Movie) {
        with(binding) {
            genreTV.text = data.genres?.joinToString(separator = ", ") { it.name.toString() } ?: ""
            statusTV.text = data.status
            taglineTV.text = data.tagline
            overview.text = data.overview
        }
    }
}