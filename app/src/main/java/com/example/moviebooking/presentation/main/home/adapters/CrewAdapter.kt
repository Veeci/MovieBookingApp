package com.example.moviebooking.presentation.main.home.adapters

import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.movie.CrewItem
import com.example.moviebooking.databinding.CastItemBinding
import com.example.moviebooking.databinding.CrewItemBinding
import com.example.moviebooking.domain.common.Const

class CrewAdapter : BaseListAdapter<CrewItem, CrewItemBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.crew_item

    override fun bindView(holder: VH, binding: CrewItemBinding, position: Int) {
        val item = getItem(position)

        with(binding) {
            profilePath.loadImage(
                source = Const.tmdbImageUrlW500 + item.profilePath,
                defaultImage = R.drawable.img_default_placeholder
            )

            name.text = item.name
            department.text = item.department
        }
    }
}