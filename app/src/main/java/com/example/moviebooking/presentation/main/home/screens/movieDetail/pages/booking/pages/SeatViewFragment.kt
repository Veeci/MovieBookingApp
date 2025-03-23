package com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.booking.pages

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseproject.domain.utils.LogUtils
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.message
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.positiveAction
import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.domain.utils.simpleAlert
import com.example.baseproject.domain.utils.title
import com.example.baseproject.domain.utils.toastShort
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.data.local.Date
import com.example.moviebooking.data.local.MultipleSeat
import com.example.moviebooking.data.local.Time
import com.example.moviebooking.databinding.FragmentSeatViewBinding
import com.example.moviebooking.domain.common.CustomSeatDrawer
import com.example.moviebooking.domain.viewmodels.BookingViewModel
import com.example.moviebooking.presentation.main.home.adapters.DateAdapter
import com.example.moviebooking.presentation.main.home.adapters.TimeAdapter
import com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.booking.BookingRouter
import com.murgupluoglu.seatview.Seat
import com.murgupluoglu.seatview.SeatViewConfig
import com.murgupluoglu.seatview.SeatViewListener

@RequiresApi(Build.VERSION_CODES.O)
class SeatViewFragment : BaseFragment<FragmentSeatViewBinding, BookingRouter, MainNavigator>(
    R.layout.fragment_seat_view
) {
    override val navigator: MainNavigator by navigatorViewModel()
    private val bookingViewModel: BookingViewModel by journeyViewModel()

    private val seats: MutableList<MultipleSeat> = mutableListOf()
    private lateinit var dateAdapter: DateAdapter
    private lateinit var timeAdapter: TimeAdapter
    private var isDateSelected = false
    private var isTimeSelected = false
    private var isSeatSelected = false

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSeatViewBinding) {
        setup()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setup() {
        with(binding) {
            seatView.seatDrawer = CustomSeatDrawer()

            seatView.seatViewListener = object : SeatViewListener<MultipleSeat> {
                override fun seatReleased(
                    releasedSeat: MultipleSeat, selectedSeats: HashSet<String>
                ) {
                    seats.remove(releasedSeat)
                    isSeatSelected = false
                }

                override fun seatSelected(
                    selectedSeat: MultipleSeat, selectedSeats: HashSet<String>
                ) {
                    if(selectedSeat.type == MultipleSeat.TYPE.UNSELECTABLE) {
                        activity.toastShort("Seat already selected or served!")
                    } else {
                        seats.add(selectedSeat)
                        isSeatSelected = true
                    }
                }

                override fun canSelectSeat(
                    clickedSeat: MultipleSeat, selectedSeats: HashSet<String>
                ): Boolean {
                    return clickedSeat.canSelect()
                }
            }

            initSeatData()

            dateAdapter = DateAdapter(
                onItemClicked = {
                    bookingViewModel.setDate(it)
                    isDateSelected = true
                }
            ).apply {
                submitList(Date().generateDates())
            }

            timeAdapter = TimeAdapter(
                onItemClicked = {
                    bookingViewModel.setTime(it)
                    isTimeSelected = true
                }
            ).apply {
                submitList(Time().generateTimes())
            }

            dateRV.apply {
                adapter = dateAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }

            timeRV.apply {
                adapter = timeAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }

            btnNext.safeClick {
                if(!isTimeSelected || !isDateSelected || !isSeatSelected) {
                    activity.simpleAlert {
                        title("Empty selection")
                        message("Please select a date, time, and seat!")
                        positiveAction("OK") {
                            dismiss()
                        }
                    }
                } else {
                    bookingViewModel.setPage(2)
                    bookingViewModel.setSeats(seats)
                }
            }
        }
    }

    private fun initSeatData() {
        val rowCount = 10
        val columnCount = 17
        val seatArray = Array(rowCount) { Array(columnCount) { MultipleSeat() } }

        for (row in 0 until rowCount) {
            for (col in 0 until columnCount) {
                val seatType = when {
                    (row == 3 && col in 12..13) -> MultipleSeat.TYPE.UNSELECTABLE
                    (row == 4 && col in 7..9) -> MultipleSeat.TYPE.UNSELECTABLE
                    (row == 4 && col == 5) -> MultipleSeat.TYPE.UNSELECTABLE
                    (row == 5 && col in 1..2) -> MultipleSeat.TYPE.UNSELECTABLE
                    (row == 5 && col in 11..12) -> MultipleSeat.TYPE.UNSELECTABLE
                    (row == 6 && col == 4) -> MultipleSeat.TYPE.UNSELECTABLE
                    (row == 6 && col in 7..8) -> MultipleSeat.TYPE.UNSELECTABLE
                    (row == 7 && col in 3..4) -> MultipleSeat.TYPE.UNSELECTABLE
                    (row == 7 && col in 13..15) -> MultipleSeat.TYPE.UNSELECTABLE
                    (row == 8 && col in 2..3) -> MultipleSeat.TYPE.UNSELECTABLE
                    (row == 8 && col == 5) -> MultipleSeat.TYPE.UNSELECTABLE
                    (row == 8 && col in 10..11) -> MultipleSeat.TYPE.UNSELECTABLE
                    (row == 8 && col == 8) -> MultipleSeat.TYPE.UNSELECTABLE
                    (row == 9 && col in 3..4) -> MultipleSeat.TYPE.UNSELECTABLE
                    (row == 9 && col in 4..5) -> MultipleSeat.TYPE.UNSELECTABLE
                    (row == 9 && col in 8..12) -> MultipleSeat.TYPE.UNSELECTABLE
                    (row == 5 && col in 9..13) -> MultipleSeat.TYPE.NOT_EXIST
                    (row == 6 && col in 1..2) -> MultipleSeat.TYPE.NOT_EXIST
                    (row == 7 && col in 7..8) -> MultipleSeat.TYPE.NOT_EXIST
                    (row == 8 && col in 1..2) -> MultipleSeat.TYPE.NOT_EXIST
                    (col == 6) -> null
                    else -> MultipleSeat.TYPE.SELECTABLE
                }
                seatArray[row][col] = MultipleSeat(
                    seatId = "$row$col",
                    seatName = "Seat ${row + 1} - ${col + 1}",
                    type = seatType ?: MultipleSeat.TYPE.NOT_EXIST
                )
            }
        }

        binding.seatView.initSeatView(
            list = seatArray.map { row -> row.map { it as Seat}.toTypedArray() }.toTypedArray(),
            optionalConfig = SeatViewConfig(
                topPadding = -10f,
                bottomPadding = -10f
            )
        )
    }
}