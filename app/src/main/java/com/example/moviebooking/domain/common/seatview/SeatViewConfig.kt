package com.murgupluoglu.seatview

import android.content.res.Resources
import android.graphics.Color
import androidx.annotation.ColorInt

/*
*  Created by Mustafa Ürgüplüoğlu on 13.03.2020.
*  Copyright © 2020 Mustafa Ürgüplüoğlu. All rights reserved.
*/

data class SeatViewConfig(
    var leftPadding: Float = 10f,
    var rightPadding: Float = 10f,
    var topPadding: Float = 10f,
    var bottomPadding: Float = 10f,

    var seatMinWidth: Float = 12f.dp2px(),
    var seatMaxWidth: Float = 30f.dp2px(),
    var seatDefaultWidth: Float = 17f.dp2px(),
    var seatWidthHeightRatio: Float = 1f,
    var seatInlineGapWidthRatio: Float = 0.265f,
    var seatNewlineGapWidthRatio: Float = 0.304f,

    @ColorInt
    var backgroundColor: Int = Color.WHITE,
    var isZoomActive: Boolean = true,
)

fun Float.dp2px(): Float {
    val scale: Float = Resources.getSystem().displayMetrics.density
    return (this * scale + 0.5f)
}