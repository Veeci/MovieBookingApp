package com.example.moviebooking.data.remote.entities.vietqr

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class Banks(
	@field:SerializedName("code")
	val code: String? = null,
	@field:SerializedName("data")
	val data: List<DataItem?>? = null,
	@field:SerializedName("desc")
	val desc: String? = null
) : Parcelable

@Parcelize
data class DataItem(
	@field:SerializedName("transferSupported")
	val transferSupported: Int? = null,
	@field:SerializedName("lookupSupported")
	val lookupSupported: Int? = null,
	@field:SerializedName("code")
	val code: String? = null,
	@field:SerializedName("isTransfer")
	val isTransfer: Int? = null,
	@field:SerializedName("bin")
	val bin: String? = null,
	@field:SerializedName("swiftCode")
	val swiftCode: String? = null,
	@field:SerializedName("name")
	val name: String? = null,
	@field:SerializedName("logo")
	val logo: String? = null,
	@field:SerializedName("shortName")
	val nameEn: String? = null,
	@field:SerializedName("id")
	val id: Int? = null,
	@field:SerializedName("short_name")
	val shortName: String? = null,
	@field:SerializedName("support")
	val support: Int? = null
) : Parcelable
