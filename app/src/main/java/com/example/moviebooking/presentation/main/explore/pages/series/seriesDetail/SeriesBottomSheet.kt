package com.example.moviebooking.presentation.main.explore.pages.series.seriesDetail

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.presentation.BaseBottomSheetDialog
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.series.Series
import com.example.moviebooking.databinding.SeriesInfoBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class SeriesBottomSheet(
    private val series: Series
) : BaseBottomSheetDialog<SeriesInfoBottomSheetBinding, MainNavigator>(
    R.layout.series_info_bottom_sheet
) {
    override val navigator: MainNavigator by journeyViewModel()

    override fun onStart() {
        super.onStart()

        dialog?.let { dialog ->
            val bottomSheet = dialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let { view ->
                val behavior = BottomSheetBehavior.from(view)
                behavior.state - BottomSheetBehavior.STATE_EXPANDED
                context?.let { context ->
                    behavior.peekHeight = (context.resources.displayMetrics.heightPixels*0.4).toInt()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        display()
    }

    private fun display() {
        with(binding) {
            overviewTV.apply {
                setShowingLine(5)
                addShowMoreText("Show more")
                addShowLessText("Show less")
                setShowMoreTextColor(R.color.ThemePrimary)
                setShowLessTextColor(R.color.ThemeTertiary)
                text = series.overview
            }

            networkRV.apply {

            }
        }
    }
}