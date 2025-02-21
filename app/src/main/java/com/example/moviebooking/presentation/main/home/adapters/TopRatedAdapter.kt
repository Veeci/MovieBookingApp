package com.example.moviebooking.presentation.main.home.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieItem
import com.example.moviebooking.databinding.ImageItemBinding
import com.example.moviebooking.domain.common.Const
import java.lang.Integer.min
import kotlin.math.roundToInt

class TopRatedAdapter: BaseListAdapter<MovieItem, ImageItemBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.image_item

    override fun getItem(position: Int): MovieItem {
        return super.getItem(position)
    }

    override fun bindView(holder: VH, binding: ImageItemBinding, position: Int) {
        val item = getItem(position)

        with(binding) {
            image.loadImage(
                source = Const.tmdbImageUrlW500 + item.posterPath,
                defaultImage = R.drawable.img_default_placeholder
            )

            image.safeClick {

            }
        }

    }
}

