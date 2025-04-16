package com.example.moviebooking.domain.common

import com.example.moviebooking.R

object Const {
    const val tmdbUrl = "https://api.themoviedb.org/3/"
    const val vietqrUrl = "https://api.vietqr.io/v2/"
    const val deepseekUrl = "https://openrouter.ai/api/v1/"

    const val tmdbAccessToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmMTdkOWJhZTJkM2YwMDZlYzk5NjQzZTlkMjAwZmI2MyIsIm5iZiI6MTczMDgxOTQ1NS4xMzQsInN1YiI6IjY3MmEzNTdmNTBlMTVlOGY1YTU4MjNiMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.C2wN9iCbXG_UEtx_JuuCowPDtV1cRqtDg6czr5Gv2ts"
    const val tmdbApiKey = "f17d9bae2d3f006ec99643e9d200fb63"
    const val tmdbImageUrlOriginal = "https://image.tmdb.org/t/p/original/"
    const val tmdbImageUrlW500 = "https://image.tmdb.org/t/p/w500/"

    const val vietqrClientId = "854f5a08-0e83-488d-9b79-c9428ba1cf46"
    const val vietqrApiKey = "7828b6c9-db33-4387-bd46-81139dc2b3f6"

    const val deepseekApiKey = "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"

    enum class DefaultAvatar(val resId: Int){
        AVATAR_1(R.drawable.default_avatar_1),
        AVATAR_2(R.drawable.default_avatar_2),
        AVATAR_3(R.drawable.default_avatar_3),
        AVATAR_4(R.drawable.default_avatar_4),
        AVATAR_5(R.drawable.default_avatar_5),
        AVATAR_6(R.drawable.default_avatar_6),
        AVATAR_7(R.drawable.default_avatar_7),
    }
}