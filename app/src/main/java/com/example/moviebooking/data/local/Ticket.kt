package com.example.moviebooking.data.local

import android.graphics.Bitmap

data class Ticket (
    val detail: Payment,
    val time: String,
    val barcode : Bitmap
)