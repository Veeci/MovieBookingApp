package com.example.moviebooking.domain.common

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProminentLayoutManager(context: Context, orientation: Int, reverseLayout: Boolean) :
    LinearLayoutManager(context, orientation, reverseLayout) {

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scroll = super.scrollHorizontallyBy(dx, recycler, state)
        scaleChildren()
        return scroll
    }

    private fun scaleChildren() {
        val mid = width / 2.0
        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childMid = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2.0
            val distance = Math.abs(mid - childMid)
            val scale = 1 - (distance / width)
            child.scaleX = scale.coerceAtLeast(0.7).toFloat()
            child.scaleY = scale.coerceAtLeast(0.7).toFloat()
        }
    }
}
