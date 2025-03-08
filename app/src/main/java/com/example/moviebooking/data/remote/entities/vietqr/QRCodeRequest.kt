package com.example.moviebooking.data.remote.entities.vietqr

import com.google.gson.annotations.SerializedName

data class QRCodeRequest(
    @SerializedName("accountNo") val accountNo: String,
    @SerializedName("accountName") val accountName: String,
    @SerializedName("acqId") val acqId: String,
    @SerializedName("addInfo") val addInfo: String,
    @SerializedName("amount") val amount: String,
    @SerializedName("template") val template: String = "compact"
)
