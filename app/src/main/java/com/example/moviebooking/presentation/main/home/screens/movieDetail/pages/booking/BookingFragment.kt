package com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.booking

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
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
                adapter = cinemaAdapter
                layoutManager = LinearLayoutManager(context)
            }

            seatView.seatViewListener = object : SeatViewListener<MultipleSeat> {
                override fun seatReleased(releasedSeat: MultipleSeat, selectedSeats: HashSet<String>) {
                    showToast("Released -> ${releasedSeat.id()}")
                }

                override fun seatSelected(selectedSeat: MultipleSeat, selectedSeats: HashSet<String>) {
                    showToast("Selected -> ${selectedSeat.id()}")
                }

                override fun canSelectSeat(clickedSeat: MultipleSeat, selectedSeats: HashSet<String>): Boolean {
                    return clickedSeat.canSelect()
                }
            }

            initSeatData()
        }
    }

    private fun initSeatData() {
        val rowCount = 5
        val columnCount = 8
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

        binding.seatView.initSeatView(seatArray as Array<Array<Seat>>)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun fetchData() {
        // Implement data fetching if needed
    }

    private fun observe() {
        // Implement observer logic if needed
    }
}
