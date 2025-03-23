package com.example.moviebooking.data.local

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.media3.common.DataReader
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)

data class Date(
    val dayOfWeek: String? = null,
    val dateNumber: Int? = null,
    var isSelected: Boolean = false
) {
    companion object {
        fun fromLocalDate(localDate: LocalDate): Date {
            val formatter = DateTimeFormatter.ofPattern("EEEE")
            return Date(
                dayOfWeek = localDate.format(formatter),
                dateNumber = localDate.dayOfMonth
            )
        }
    }

    fun generateDates(): List<Date> {
        val today = LocalDate.now()
        return (0..6).map { today.plusDays(it.toLong()) }.map { Date.fromLocalDate(it) }
    }

    fun displayFormat(): String {
        return "$dayOfWeek \n$dateNumber"
    }
}