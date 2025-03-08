package com.example.moviebooking.presentation.main.home.adapters

import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.R
import com.example.moviebooking.data.local.Combo
import com.example.moviebooking.databinding.ComboItemBinding

class ComboAdapter : BaseListAdapter<Combo, ComboItemBinding>() {
    private val quantities = mutableMapOf<Combo, Int>()

    var onQuantityChangeListener: ((Combo, Int) -> Unit)? = null

    override fun getLayoutId(viewType: Int) = R.layout.combo_item

    override fun bindView(holder: VH, binding: ComboItemBinding, position: Int) {
        val item = getItem(position)

        if(!quantities.containsKey(item)) {
            quantities[item] = 0
        }

        updateQuantity(binding, item)

        with(binding) {
            imageResIdIV.loadImage(
                source = item.imageResId,
                defaultImage = R.drawable.img_default_placeholder
            )

            nameTV.text = item.name
            descriptionTV.text = item.description

            btnIncrease.safeClick {
                val currentQuantity = quantities[item] ?: 0
                quantities[item] = currentQuantity + 1
                updateQuantity(binding, item)
                onQuantityChangeListener?.invoke(item, quantities[item] ?: 0)
            }

            btnDecrease.safeClick {
                val currentQuantity = quantities[item] ?: 0
                if (currentQuantity > 0) {
                    quantities[item] = currentQuantity - 1
                    updateQuantity(binding, item)
                    onQuantityChangeListener?.invoke(item, quantities[item] ?: 0)
                }
            }
        }
    }

    private fun updateQuantity(binding: ComboItemBinding, item: Combo) {
        binding.quantityTV.text = (quantities[item] ?: 0).toString()
    }

    fun getSelectedCombos(): List<Combo> {
        return quantities.filter { it.value > 0 }
            .map { it.key.copy(quantity = it.value) }
    }
}