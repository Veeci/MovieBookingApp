package com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.booking

import android.animation.Animator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseproject.domain.utils.gone
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.visible
import com.example.baseproject.presentation.BaseFragment
import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.data.local.Cinema
import com.example.moviebooking.data.local.CinemaData
import com.example.moviebooking.data.local.MultipleSeat
import com.example.moviebooking.databinding.FragmentBookingBinding
import com.example.moviebooking.domain.viewmodels.MovieViewModel
import com.example.moviebooking.presentation.main.home.adapters.CinemaAdapter
import com.murgupluoglu.seatview.Seat
import com.murgupluoglu.seatview.SeatView
import com.murgupluoglu.seatview.SeatViewListener
import com.murgupluoglu.seatview.extensions.CenterLinesExtension
import com.murgupluoglu.seatview.extensions.DebugExtension

@Suppress("UNCHECKED_CAST")
class BookingFragment : BaseFragment<FragmentBookingBinding, BookingRouter, MainNavigator>(
    R.layout.fragment_booking
) {
    override val navigator: MainNavigator by navigatorViewModel()
    private val movieViewmodel: MovieViewModel by journeyViewModel()

    private var isExpanded = false
    private var selectedCinema: Cinema? = null

    override fun initView(savedInstanceState: Bundle?, binding: FragmentBookingBinding) {
        setup()
        fetchData()
        observe()
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
                            toggleSeatBookingScene()
                        }

                    }
                }
            }

            seatView.seatViewListener = object : SeatViewListener<MultipleSeat> {
                override fun seatReleased(releasedSeat: MultipleSeat, selectedSeats: HashSet<String>) {
                }

                override fun seatSelected(selectedSeat: MultipleSeat, selectedSeats: HashSet<String>) {
                }

                override fun canSelectSeat(clickedSeat: MultipleSeat, selectedSeats: HashSet<String>): Boolean {
                    return clickedSeat.canSelect()
                }
            }

            initSeatData()
        }
    }

    private fun initSeatData() {
        val rowCount = 15
        val columnCount = 18
        val seatArray = Array(rowCount) { Array(columnCount) { MultipleSeat() } }

        for (row in 0 until rowCount) {
            for (col in 0 until columnCount) {
                val seat = MultipleSeat(
                    seatId = "R$row-C$col",
                    seatName = "Seat ${row + 1}-${col + 1}",
                    type = if ((row + col) % 3 == 0) MultipleSeat.TYPE.UNSELECTABLE else MultipleSeat.TYPE.SELECTABLE,
                    isPreSelectedSeat = false
                )
                seatArray[row][col] = seat
            }
        }

        binding.seatView.initSeatView(seatArray.map { row -> row.map { it as Seat }.toTypedArray() }.toTypedArray())
    }

    private fun fetchData() {
        // Implement data fetching if needed
    }

    private fun observe() {
        // Implement observer logic if needed
    }

    private fun toggleSeatBookingScene() {
        isExpanded = !isExpanded
        if (isExpanded) {

        } else {

        }
    }
}
