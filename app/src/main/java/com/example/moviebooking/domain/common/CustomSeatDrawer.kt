@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.moviebooking.domain.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import androidx.core.content.ContextCompat
import com.example.moviebooking.R
import com.example.moviebooking.data.local.MultipleSeat
import com.murgupluoglu.seatview.Seat
import com.murgupluoglu.seatview.SeatViewConfig
import com.murgupluoglu.seatview.SeatViewParameters
import com.murgupluoglu.seatview.seatdrawer.SeatDrawer

class CustomSeatDrawer : SeatDrawer {
    override fun <SEAT : Seat> draw(
        context: Context,
        params: SeatViewParameters,
        config: SeatViewConfig,
        canvas: Canvas,
        seat: SEAT,
        seatRectF: RectF,
        isSelected: Boolean
    ) {
        val multipleSeat = seat as? MultipleSeat ?: return

        val seatDrawable = when {
            isSelected -> ContextCompat.getDrawable(context, R.drawable.ic_seat_selected)
            multipleSeat.type == MultipleSeat.TYPE.UNSELECTABLE -> ContextCompat.getDrawable(context, R.drawable.ic_seat_available)
            multipleSeat.type == MultipleSeat.TYPE.SELECTABLE -> ContextCompat.getDrawable(context, R.drawable.ic_seat_unavailable)
            multipleSeat.type == MultipleSeat.TYPE.NOT_EXIST  -> ContextCompat.getDrawable(context, R.drawable.ic_seat_not_exist)
            else -> null
        }

        seatDrawable?.let {
            val scaleFactor = 2.0f
            val paddingFactor = 0.5f
            val centerX = seatRectF.centerX()
            val centerY = seatRectF.centerY()
            val scaledWidth = seatRectF.width() * scaleFactor
            val scaledHeight = seatRectF.height() * scaleFactor

            val paddingWidth = scaledWidth * paddingFactor
            val paddingHeight = scaledHeight * paddingFactor

            val left = (centerX - scaledWidth / 2 + paddingWidth / 2).toInt()
            val top = (centerY - scaledHeight / 2 + paddingHeight / 2).toInt()
            val right = (centerX + scaledWidth / 2 - paddingWidth / 2).toInt()
            val bottom = (centerY + scaledHeight / 2 - paddingHeight / 2).toInt()

            it.bounds = Rect(left, top, right, bottom)
            it.draw(canvas)
        }
    }
}