package com.example.moviebooking.data.local

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
data class Time (
    val time: String? = null,
    var isSelected: Boolean = false
) {
    companion object {
        fun fromLocalTime(localTime: LocalTime) : Time {
            val formatter = DateTimeFormatter.ofPattern("hh:mm a")
            return Time(time = localTime.format(formatter))
        }
    }

    fun generateTimes(): List<Time> {
        val times = mutableListOf<Time>()
        val startHour = 0
        val endHour = 23

        var time = LocalTime.of(startHour, 0)

        while (time.hour < endHour) {
            times.add(Time.fromLocalTime(time))
            time = time.plusHours(1)
        }

        return times
    }
}