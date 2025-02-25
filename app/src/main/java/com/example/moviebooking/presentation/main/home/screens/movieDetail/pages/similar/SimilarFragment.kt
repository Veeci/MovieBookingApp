package com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.similar

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentSimilarBinding
import com.example.moviebooking.domain.viewmodels.MovieViewModel

class SimilarFragment : BaseFragment<FragmentSimilarBinding, SimilarRouter, MainNavigator> (
    R.layout.fragment_similar
) {
    override val navigator: MainNavigator by navigatorViewModel()
    private val movieViewmodel: MovieViewModel by journeyViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSimilarBinding) {
        setup()
        fetchData()
        observe()
    }

    private fun setup() {

    }

    private fun fetchData() {

    }

    private fun observe() {

    }
}