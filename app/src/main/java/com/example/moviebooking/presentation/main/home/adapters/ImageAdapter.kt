package com.example.moviebooking.presentation.main.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.movie.Image
import com.example.moviebooking.data.remote.entities.tmdb.movie.PostersItem
import com.example.moviebooking.databinding.ImageItemBinding
import com.example.moviebooking.domain.common.Const

class ImageAdapter : BaseListAdapter<PostersItem, ImageItemBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.image_item

    override fun bindView(holder: VH, binding: ImageItemBinding, position: Int) {
        val item = getItem(position)

        binding.root.post {
            val aspectRatio = item.aspectRatio ?: 1.0
            val layoutParams = binding.image.layoutParams
            layoutParams.width = RecyclerView.LayoutParams.MATCH_PARENT
            layoutParams.height = (layoutParams.width / aspectRatio).toInt()
            binding.image.layoutParams = layoutParams
        }

        binding.image.loadImage(
            source = Const.tmdbImageUrlW500 + item.filePath,
            defaultImage = R.drawable.img_default_placeholder
        )
    }
}
