package com.example.moviebooking.presentation.main.home.adapters

import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.series.EpisodesItem
import com.example.moviebooking.databinding.EpisodeItemBinding
import com.example.moviebooking.domain.common.Const

class EpisodeAdapter : BaseListAdapter<EpisodesItem, EpisodeItemBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.episode_item

    override fun bindView(holder: VH, binding: EpisodeItemBinding, position: Int) {
        val item = getItem(position)

        with(binding) {
            episodeName.text = "Episode ${item.episodeNumber}: ${item.name}"
            overview.text = item.overview
            runtimeTV.text = item.runtime.toString()
            airDateTV.text = "Air date:  ${item.airDate}"
            stillpathIV.loadImage(
                source = Const.tmdbImageUrlOriginal + item.stillPath,
                defaultImage = R.drawable.img_default_placeholder
            )
        }
    }
}