package com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.booking.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.data.local.Cinema
import com.example.moviebooking.data.local.CinemaData
import com.example.moviebooking.data.local.MultipleSeat
import com.example.moviebooking.databinding.FragmentCinemaBinding
import com.example.moviebooking.domain.viewmodels.BookingViewModel
import com.example.moviebooking.domain.viewmodels.MovieViewModel
import com.example.moviebooking.presentation.main.home.adapters.CinemaAdapter
import com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.booking.BookingFragment
import com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.booking.BookingRouter
import com.murgupluoglu.seatview.Seat
import com.murgupluoglu.seatview.SeatViewListener

class CinemaFragment : BaseFragment<FragmentCinemaBinding, BookingRouter, MainNavigator>(
    R.layout.fragment_cinema
) {
    override val navigator: MainNavigator by navigatorViewModel()
    private val movieViewmodel: MovieViewModel by journeyViewModel()
    private val bookingViewModel: BookingViewModel by journeyViewModel()

    private var isExpanded = false
    private var selectedCinema: Cinema? = null

    override fun initView(savedInstanceState: Bundle?, binding: FragmentCinemaBinding) {
        setup()
    }

    private fun setup() {
        val cinemaAdapter = CinemaAdapter()
        cinemaAdapter.submitList(CinemaData.cinemaList)

        with(binding) {
            cinemaRV.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = cinemaAdapter.apply {
                    action = object : BaseListAdapter.Action<Cinema> {
                        override fun click(position: Int, data: Cinema, code: Int) {
                            bookingViewModel.setPage(1)
                            bookingViewModel.setCinema(data)
                        }
                    }
                }
            }
        }
    }
}