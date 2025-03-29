package com.example.moviebooking.presentation.main.home.adapters

import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.people.PeopleItem
import com.example.moviebooking.databinding.CelebrityItemBinding
import com.example.moviebooking.domain.common.Const

class CelebrityAdapter(
    private val onItemClicked: (PeopleItem) -> Unit
) : BaseListAdapter<PeopleItem, CelebrityItemBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.celebrity_item

    override fun bindView(holder: VH, binding: CelebrityItemBinding, position: Int) {
        val item = getItem(position)

        with(binding) {
            nameTV.text = item.name
            knownForDepartmentTV.text = item.knownForDepartment

            profilePath.loadImage(
                source = Const.tmdbImageUrlW500 + item.profilePath,
                defaultImage = R.drawable.img_default_placeholder
            )

            root.safeClick { onItemClicked(item) }
        }
    }
}