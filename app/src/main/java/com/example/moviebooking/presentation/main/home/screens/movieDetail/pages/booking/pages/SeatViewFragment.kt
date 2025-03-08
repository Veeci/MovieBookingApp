package com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.booking.pages

import android.os.Bundle
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.data.local.MultipleSeat
import com.example.moviebooking.databinding.FragmentSeatViewBinding
import com.example.moviebooking.domain.viewmodels.BookingViewModel
import com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.booking.BookingRouter
import com.murgupluoglu.seatview.Seat
import com.murgupluoglu.seatview.SeatViewListener

class SeatViewFragment : BaseFragment<FragmentSeatViewBinding, BookingRouter, MainNavigator>(
    R.layout.fragment_seat_view
) {
    override val navigator: MainNavigator by navigatorViewModel()
    private val bookingViewModel: BookingViewModel by journeyViewModel()

    private val seats: MutableList<MultipleSeat> = mutableListOf()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSeatViewBinding) {
        setup()
    }

    private fun setup() {
        with(binding) {
            seatView.seatViewListener = object : SeatViewListener<MultipleSeat> {
                override fun seatReleased(
                    releasedSeat: MultipleSeat, selectedSeats: HashSet<String>
                ) {
                    seats.remove(releasedSeat)
                }

                override fun seatSelected(
                    selectedSeat: MultipleSeat, selectedSeats: HashSet<String>
                ) {
                    seats.add(selectedSeat)
                }

                override fun canSelectSeat(
                    clickedSeat: MultipleSeat, selectedSeats: HashSet<String>
                ): Boolean {
                    return clickedSeat.canSelect()
                }
            }

            initSeatData()

            btnNext.safeClick {
                bookingViewModel.setPage(2)
                bookingViewModel.setSeats(seats)
            }
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

        binding.seatView.initSeatView(seatArray.map { row -> row.map { it as Seat }.toTypedArray() }
            .toTypedArray())
    }
}