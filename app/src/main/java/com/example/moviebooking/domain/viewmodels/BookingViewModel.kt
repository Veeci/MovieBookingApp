package com.example.moviebooking.domain.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.viewmodel.BaseViewModel
import com.example.moviebooking.data.local.Cinema
import com.example.moviebooking.data.local.Combo
import com.example.moviebooking.data.local.Date
import com.example.moviebooking.data.local.MultipleSeat
import com.example.moviebooking.data.local.Payment
import com.example.moviebooking.data.local.Ticket
import com.example.moviebooking.data.local.Time
import com.example.moviebooking.data.remote.entities.vietqr.QRCode
import com.example.moviebooking.domain.usecases.booking.BookingUseCase

class BookingViewModel(
    private val bookingUseCase: BookingUseCase
) : BaseViewModel() {
    private val _currentPage = MutableLiveData<Int>()
    val currentPage: LiveData<Int> = _currentPage

    private val _cinema = MutableLiveData<Cinema>()
    val cinema: LiveData<Cinema> = _cinema

    private val _seats = MutableLiveData<List<MultipleSeat>>()
    val seats: LiveData<List<MultipleSeat>> = _seats

    private val _combo = MutableLiveData<List<Combo>>()
    val combo: LiveData<List<Combo>> = _combo

    private val _qrCode = MutableLiveData<ResponseStatus<QRCode>>()
    val qrCode: LiveData<ResponseStatus<QRCode>> = _qrCode

    private val _ticket = MutableLiveData<List<Ticket>>()
    val ticket: LiveData<List<Ticket>> = _ticket

    private val _date = MutableLiveData<Date>()
    val date: LiveData<Date> = _date

    private val _time = MutableLiveData<Time>()
    val time: LiveData<Time> = _time

    fun setPage(page: Int) {
        _currentPage.value = page
    }

    fun setCinema(cinema: Cinema) {
        _cinema.value = cinema
    }

    fun setSeats(seats: List<MultipleSeat>) {
        _seats.value = seats
    }

    fun setCombo(combo: List<Combo>) {
        _combo.value = combo
    }

    fun getQRCode(payment: Payment) {
        launchCoroutine {
            bookingUseCase.bookMovie(payment).collect { response ->
                _qrCode.postValue(response)
            }
        }
    }

    fun setDate(date: Date) {
        _date.value = date
    }

    fun setTime(time: Time) {
        _time.value = time
    }

    fun createTicket(ticket: Ticket) {
        val updatedList = _ticket.value.orEmpty().toMutableList()
        updatedList.add(ticket)
        _ticket.value = updatedList
    }
}