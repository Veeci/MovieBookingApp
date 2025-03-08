package com.example.moviebooking.domain.usecases.booking

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.local.Payment
import com.example.moviebooking.data.remote.entities.vietqr.QRCode
import kotlinx.coroutines.flow.Flow

interface BookingUseCase {
    fun bookMovie(payment: Payment) : Flow<ResponseStatus<QRCode>>
}