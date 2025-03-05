package com.example.moviebooking.data.local

import com.example.moviebooking.R

data class Cinema(
    val name: String,
    val address: String,
    val logo: Int
)

enum class CinemaChain {
    CGV, LOTTE, GALAXY, BHD
}

object CinemaData {
    val cinemaList = listOf(
        // CGV Cinemas
        Cinema("CGV Vincom Center Bà Triệu", "6th Floor, Vincom Center, 191 Bà Triệu, Hà Nội", R.drawable.cgv_logo),
        Cinema("CGV Vincom Nguyễn Chí Thanh", "5th Floor, Vincom Center, 54A Nguyễn Chí Thanh, Hà Nội", R.drawable.cgv_logo),
        Cinema("CGV Hồ Gươm Plaza", "3rd Floor, Hồ Gươm Plaza, 102 Trần Phú, Hà Đông, Hà Nội", R.drawable.cgv_logo),

        // LOTTE Cinemas
        Cinema("Lotte Cinema Landmark", "5th Floor, Keangnam Landmark 72, Phạm Hùng, Hà Nội", R.drawable.lotte_logo),
        Cinema("Lotte Cinema Hà Đông", "4th Floor, Mê Linh Plaza Hà Đông, Hà Nội", R.drawable.lotte_logo),
        Cinema("Lotte Cinema Mipec Tower", "5th Floor, Mipec Tower, 229 Tây Sơn, Hà Nội", R.drawable.lotte_logo),

        // Galaxy Cinemas
        Cinema("Galaxy Tràng Thi", "1st Floor, 87 Láng Hạ, Hà Nội", R.drawable.galaxy_logo),
        Cinema("Galaxy Mipec", "5th Floor, Mipec Tower, 229 Tây Sơn, Hà Nội", R.drawable.galaxy_logo),
        Cinema("Galaxy Nguyễn Du", "1st Floor, 19 Nguyễn Du, Hà Nội", R.drawable.galaxy_logo)
    )
}
