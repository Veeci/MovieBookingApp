package com.example.moviebooking.domain.usecases.booking

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.local.Payment
import com.example.moviebooking.data.remote.entities.vietqr.QRCode
import com.example.moviebooking.data.remote.entities.vietqr.QRCodeRequest
import com.example.moviebooking.data.remote.services.vietqr.VietQRService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class BookingUseCaseImpl(
    private val apiService: VietQRService
) : BookingUseCase, ApiHandler {
    override fun bookMovie(payment: Payment): Flow<ResponseStatus<QRCode>> {
        return flow {
            emit(handleApi { apiService.generateQRCode(
                QRCodeRequest(
                    accountNo = "0611001917137",
                    accountName = "QUY TRO NGHEO VUNG CAO",
                    acqId = "970436",
                    addInfo = payment.paymentInfo(),
                    amount = payment.paymentAmount()
                )
            ) })
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch { e ->
            emit(ResponseStatus.Error(
                message = e.message ?: "Something went wrong"
            ))
        }.flowOn(Dispatchers.IO)
    }
}