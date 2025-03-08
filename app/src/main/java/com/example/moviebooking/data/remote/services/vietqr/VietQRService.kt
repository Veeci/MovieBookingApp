package com.example.moviebooking.data.remote.services.vietqr

import com.example.moviebooking.data.remote.entities.vietqr.Banks
import com.example.moviebooking.data.remote.entities.vietqr.QRCode
import com.example.moviebooking.data.remote.entities.vietqr.QRCodeRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface VietQRService {

    @GET("banks/")
    fun getBanks(): Response<List<Banks>>

    @GET("generate/")
    fun generateQRCode(
        @Body request: QRCodeRequest
    ): Response<QRCode>
}