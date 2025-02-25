package com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentBookingBinding
import com.example.moviebooking.domain.viewmodels.MovieViewModel

class BookingFragment : BaseFragment<FragmentBookingBinding, BookingRouter, MainNavigator> (
    R.layout.fragment_booking
) {
    override val navigator: MainNavigator by navigatorViewModel()
    private val movieViewmodel: MovieViewModel by journeyViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentBookingBinding) {
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