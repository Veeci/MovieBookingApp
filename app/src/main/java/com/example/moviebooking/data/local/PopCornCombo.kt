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
    val imageResId: Int? = null,
    val quantity: Int? = 0
)

data class Soda(
    val name: String,
    val price: Double,
    val size: Size,
)

data class PopCorn(
    val name: String,
    val price: Double,
    val size: Size,
    val flavor: String? = null,
)

object ComboData {
    val comboList = listOf(
        Combo(
            name = "Combo 1",
            popCorn = listOf(
                PopCorn(name = "Caramel Popcorn", price = 7.0, size = Size.LARGE, flavor = "Caramel"),
            ),
            soda = listOf(
                Soda(name = "Coca-Cola", price = 5.0, size = Size.LARGE),
            ),
            price = 10.0,
            description = "Large Caramel Popcorn + Large Coca-Cola",
            imageResId = R.drawable.combo_4
        ),
        Combo(
            name = "Combo 2",
            popCorn = listOf(
                PopCorn(name = "Cheese Popcorn", price = 6.0, size = Size.MEDIUM, flavor = "Cheese"),
            ),
            soda = listOf(
                Soda(name = "Sprite", price = 4.0, size = Size.MEDIUM),
            ),
            price = 9.0,
            description = "Medium Cheese Popcorn + Medium Sprite",
            imageResId = R.drawable.combo_2
        ),
        Combo(
            name = "Combo 3",
            popCorn = listOf(
                PopCorn(name = "Salted Popcorn", price = 5.0, size = Size.SMALL, flavor = "Salted"),
            ),
            soda = listOf(
                Soda(name = "Fanta", price = 3.0, size = Size.SMALL),
            ),
            price = 7.0,
            description = "Small Salted Popcorn + Small Fanta",
            imageResId = R.drawable.combo_3
        ),
        Combo(
            name = "Combo 4",
            popCorn = listOf(
                PopCorn(name = "Caramel Popcorn", price = 7.0, size = Size.LARGE, flavor = "Caramel"),
                PopCorn(name = "Cheese Popcorn", price = 6.0, size = Size.MEDIUM, flavor = "Cheese")
            ),
            soda = listOf(
                Soda(name = "Coca-Cola", price = 5.0, size = Size.LARGE),
                Soda(name = "Sprite", price = 4.0, size = Size.MEDIUM)
            ),
            price = 20.0,
            description = "Large Caramel Popcorn + Medium Cheese Popcorn + Large Coca-Cola + Medium Sprite",
            imageResId = R.drawable.combo_4
        ),
        Combo(
            name = "Combo 5",
            popCorn = listOf(
                PopCorn(name = "Salted Popcorn", price = 5.0, size = Size.SMALL, flavor = "Salted"),
                PopCorn(name = "Caramel Popcorn", price = 7.0, size = Size.LARGE, flavor = "Caramel")
            ),
            soda = listOf(
                Soda(name = "Fanta", price = 3.0, size = Size.SMALL),
                Soda(name = "Coca-Cola", price = 5.0, size = Size.LARGE)
            ),
            price = 15.0,
            description = "Small Salted Popcorn + Large Caramel Popcorn + Small Fanta + Large Coca-Cola",
            imageResId = R.drawable.combo_3
        )
    )
}