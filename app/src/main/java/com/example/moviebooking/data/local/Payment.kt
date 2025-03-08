package com.example.moviebooking.data.local

import com.example.moviebooking.data.remote.entities.tmdb.movie.Movie
import kotlin.math.roundToInt

data class Payment(
    val movie: Movie,
    val cinema: Cinema,
    val seats: List<MultipleSeat>,
    val popcorn: List<Combo>
) {
    fun paymentInfo(): String {
        val stringBuilder = StringBuilder()

        // Movie Information
        stringBuilder.append("Movie: ${movie.title}\n")
        stringBuilder.append("Release Date: ${movie.releaseDate}\n")

        // Cinema Information
        stringBuilder.append("Cinema: ${cinema.name}\n")
        stringBuilder.append("Address: ${cinema.address}\n")

        // Seats Information
        stringBuilder.append("Seats:\n")
        if (seats.isNotEmpty()) {
            seats.forEach { seat ->
                stringBuilder.append("- ${seat.seatName}, type: ${seat.type}")
            }
        } else {
            stringBuilder.append("- No seats selected\n")
        }

        // Popcorn/Combo Information
        stringBuilder.append("Combos:\n")
        if (popcorn.isNotEmpty()) {
            popcorn.forEach { combo ->
                stringBuilder.append("- ${combo.name} - Price: ${combo.price}\n")
                combo.popCorn?.forEach { popCorn ->
                    stringBuilder.append("  - Popcorn: ${popCorn.name} - Size: ${popCorn.size} - Flavor: ${popCorn.flavor}\n")
                }
                combo.soda?.forEach { soda ->
                    stringBuilder.append("  - Soda: ${soda.name} - Size: ${soda.size}\n")
                }
            }
        } else {
            stringBuilder.append("- No combos selected\n")
        }

        return stringBuilder.toString()
    }

    fun paymentAmount(): String {
        var totalAmount = 0.0

        totalAmount += seats.size * 55
        totalAmount += popcorn.sumOf { it.price }

        return totalAmount.roundToInt().toString()
    }
}