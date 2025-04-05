package com.example.moviebooking.presentation.main.home.adapters

import com.example.baseproject.domain.utils.gone
import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.domain.utils.visible
import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.series.EpisodesItem
import com.example.moviebooking.data.remote.entities.tmdb.series.SeasonsItem
import com.example.moviebooking.databinding.SeasonItemBinding
import com.example.moviebooking.domain.common.Const

class SeasonAdapter(
    private val onMoreSlicked: (SeasonsItem) -> List<EpisodesItem>
) : BaseListAdapter<SeasonsItem, SeasonItemBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.season_item

    override fun bindView(holder: VH, binding: SeasonItemBinding, position: Int) {
        val item = getItem(position)

        with(binding) {
            posterPathIV.loadImage(
                source = Const.tmdbImageUrlOriginal + item.posterPath,
                defaultImage = R.drawable.img_default_placeholder
            )

            nameTV.text = item.name
            overviewTV.text = item.overview
            seasonNumberTV.text = binding.root.context.getString(R.string.season, (item.seasonNumber?.plus(1)))
            episodeCountTV.text = binding.root.context.getString(R.string.number_of_episodes, item.episodeCount)

            val episodeAdapter = EpisodeAdapter()
            episodeAdapter.submitList(onMoreSlicked(item))
            binding.episodeRV.apply {
                adapter = episodeAdapter
            }

            btnMore.safeClick {
                btnMore.gone()
                episodeList.visible()
                onMoreSlicked(item)
            }

            btnMinimize.safeClick {
                btnMore.visible()
                episodeList.gone()
            }
        }
    }
}