package com.example.moviebooking.presentation.main.home.adapters

import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.movie.CastItem
import com.example.moviebooking.data.remote.entities.tmdb.movie.Credit
import com.example.moviebooking.databinding.CastItemBinding
import com.example.moviebooking.domain.common.Const

class CastAdapter : BaseListAdapter<CastItem, CastItemBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.cast_item

    override fun bindView(holder: VH, binding: CastItemBinding, position: Int) {
        val item = getItem(position)

        with(binding) {
            profilePath.loadImage(
                source = Const.tmdbImageUrlW500 + item.profilePath,
                defaultImage = R.drawable.img_default_placeholder
            )

            item.name?.let { name.text = it }
        }
    }
}