package com.example.moviebooking.presentation.main.explore.pages.series.seriesDetail

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.MainRouter
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.series.Series
import com.example.moviebooking.data.remote.entities.tmdb.series.SeriesItem
import com.example.moviebooking.databinding.FragmentCelebrityDetailBinding
import com.example.moviebooking.databinding.FragmentSeriesDetailBinding
import com.example.moviebooking.domain.common.Const
import com.example.moviebooking.domain.viewmodels.SeriesViewModel

class SeriesDetailScreen :
    BaseFragment<FragmentSeriesDetailBinding, SeriesDetailRouter, MainNavigator>(
        R.layout.fragment_series_detail
    ) {
    override val navigator: MainNavigator by journeyViewModel()
    override val viewModel by journeyViewModel<SeriesViewModel>()

    private var seriesID: String = ""
    private var bottomSheet = SeriesBottomSheet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        seriesID = arguments?.getString("seriesID") ?: ""
        val previousSheet = parentFragmentManager.findFragmentByTag(SeriesBottomSheet::class.java.name)
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
    }

    private fun observer() {
        viewModel.seriesDetail.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> { showLoading(isLoading = true, preventClicking = true) }
                is ResponseStatus.Error -> { showLoading(isLoading = false) }
                is ResponseStatus.Success -> {
                    showLoading(isLoading = false)
                    displaySeriesInfo(status.data)

                    if(bottomSheet.isAdded) {
                        bottomSheet.dismissAllowingStateLoss()
                    }

                    bottomSheet = SeriesBottomSheet(status.data)
                    bottomSheet.show(parentFragmentManager, SeriesBottomSheet::class.java.name)
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


            }
        }
    }

    private fun onClickListener() {
        with(binding) {
            btnBack.setOnClickListener { navigator.onPopScreen() }
        }
    }
}