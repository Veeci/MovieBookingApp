package com.example.moviebooking.presentation.main.home.adapters

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.ReviewItem
import com.example.moviebooking.databinding.ReviewItemBinding
import com.example.moviebooking.domain.common.Const
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
class ReviewAdapter : BaseListAdapter<ReviewItem, ReviewItemBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.review_item


    override fun bindView(holder: VH, binding: ReviewItemBinding, position: Int) {
        val item = getItem(position)

        with(binding) {
            avatarPath.loadImage(
                source = Const.tmdbImageUrlW500 + item.authorDetails?.avatarPath,
                defaultImage = R.drawable.img_default_placeholder
            )
            rating.text = item.authorDetails?.rating.toString()
            createdAt.text = formatDateAndTime(item.createdAt ?: "")
            author.text = item.author

            content.apply {
                setShowingLine(5)
                addShowMoreText("Show more")
                addShowLessText("Show less")
                setShowMoreTextColor(R.color.ThemePrimary)
                setShowLessTextColor(R.color.ThemeTertiary)
                text = item.content
            }


        }
    }

    private fun formatDateAndTime(timestampString: String): String {
        val instant: Instant = Instant.parse(timestampString)
        val localDateTime: LocalDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.ENGLISH)
        val formattedDateTime: String = localDateTime.format(formatter)

        return formattedDateTime
    }
}