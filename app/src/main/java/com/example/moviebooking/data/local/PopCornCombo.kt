package com.example.moviebooking.data.local

import com.example.moviebooking.R

enum class Size {
    SMALL,
    MEDIUM,
    LARGE
}

data class Combo(
    val name: String,
    val popCorn: List<PopCorn>? = null,
    val soda: List<Soda>? = null,
    val price: Double,
    val description: String? = null,
    val image: String? = null,
    val imageResId: Int? = null
)

data class Soda(
    val name: String,
    val price: Double,
    val size: Size,
    val imageResId: Int? = null
)

data class PopCorn(
    val name: String,
    val price: Double,
    val size: Size,
    val flavor: String? = null,
    val imageResId: Int? = null
)

object ComboData {
    val comboList = listOf(
        Combo(
            name = "Combo 1",
            popCorn = listOf(
                PopCorn(name = "Caramel Popcorn", price = 7.0, size = Size.LARGE, flavor = "Caramel", imageResId = R.drawable.popcorn_caramel),
            ),
            soda = listOf(
                Soda(name = "Coca-Cola", price = 5.0, size = Size.LARGE, imageResId = R.drawable.coke),
            ),
            price = 10.0,
            description = "Large Caramel Popcorn + Large Coca-Cola",
            imageResId = R.drawable.combo_1
        ),
        Combo(
            name = "Combo 2",
            popCorn = listOf(
                PopCorn(name = "Cheese Popcorn", price = 6.0, size = Size.MEDIUM, flavor = "Cheese", imageResId = R.drawable.popcorn_cheese),
            ),
            soda = listOf(
                Soda(name = "Sprite", price = 4.0, size = Size.MEDIUM, imageResId = R.drawable.sprite),
            ),
            price = 9.0,
            description = "Medium Cheese Popcorn + Medium Sprite",
            imageResId = R.drawable.combo_2
        ),
        Combo(
            name = "Combo 3",
            popCorn = listOf(
                PopCorn(name = "Salted Popcorn", price = 5.0, size = Size.SMALL, flavor = "Salted", imageResId = R.drawable.popcorn_salted),
            ),
            soda = listOf(
                Soda(name = "Fanta", price = 3.0, size = Size.SMALL, imageResId = R.drawable.fanta),
            ),
            price = 7.0,
            description = "Small Salted Popcorn + Small Fanta",
            imageResId = R.drawable.combo_3
        ),
        Combo(
            name = "Combo 4",
            popCorn = listOf(
                PopCorn(name = "Caramel Popcorn", price = 7.0, size = Size.LARGE, flavor = "Caramel", imageResId = R.drawable.popcorn_caramel),
                PopCorn(name = "Cheese Popcorn", price = 6.0, size = Size.MEDIUM, flavor = "Cheese", imageResId = R.drawable.popcorn_cheese)
            ),
            soda = listOf(
                Soda(name = "Coca-Cola", price = 5.0, size = Size.LARGE, imageResId = R.drawable.coke),
                Soda(name = "Sprite", price = 4.0, size = Size.MEDIUM, imageResId = R.drawable.sprite)
            ),
            price = 20.0,
            description = "Large Caramel Popcorn + Medium Cheese Popcorn + Large Coca-Cola + Medium Sprite",
            imageResId = R.drawable.combo_4
        ),
        Combo(
            name = "Combo 5",
            popCorn = listOf(
                PopCorn(name = "Salted Popcorn", price = 5.0, size = Size.SMALL, flavor = "Salted", imageResId = R.drawable.popcorn_salted),
                PopCorn(name = "Caramel Popcorn", price = 7.0, size = Size.LARGE, flavor = "Caramel", imageResId = R.drawable.popcorn_caramel)
            ),
            soda = listOf(
                Soda(name = "Fanta", price = 3.0, size = Size.SMALL, imageResId = R.drawable.fanta),
                Soda(name = "Coca-Cola", price = 5.0, size = Size.LARGE, imageResId = R.drawable.coke)
            ),
            price = 15.0,
            description = "Small Salted Popcorn + Large Caramel Popcorn + Small Fanta + Large Coca-Cola",
            imageResId = R.drawable.combo_5
        )
    )
}