package com.example.moviebooking.data.remote.entities.vietqr

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class QRCode(
	val code: String? = null,
	val data: Data? = null,
	val desc: String? = null
) : Parcelable

@Parcelize
data class Data(
	val qrCode: String? = null,
	val qrDataURL: String? = null
) : Parcelable
