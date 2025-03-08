package com.example.moviebooking.data.remote.services.vietqr

import com.example.moviebooking.data.remote.entities.vietqr.Banks
import com.example.moviebooking.data.remote.entities.vietqr.QRCode
import com.example.moviebooking.data.remote.entities.vietqr.QRCodeRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface VietQRService {

    @GET("banks/")
    suspend fun getBanks(): Response<List<Banks>>

    @POST("generate/")
    suspend fun generateQRCode(
        @Body request: QRCodeRequest
    ): Response<QRCode>
}