package com.example.moviebooking.presentation.main.home

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.baseproject.domain.utils.EndlessOnScrollListener
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.gone
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.message
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.negativeAction
import com.example.baseproject.domain.utils.positiveAction
import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.domain.utils.showKeyboard
import com.example.baseproject.domain.utils.showKeyboardSearchView
import com.example.baseproject.domain.utils.simpleAlert
import com.example.baseproject.domain.utils.title
import com.example.baseproject.presentation.BaseFragment
import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.data.local.Banner
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieItem
import com.example.moviebooking.databinding.FragmentHomeScreenBinding
import com.example.moviebooking.domain.common.Const
import com.example.moviebooking.domain.common.RecyclerSnapItemListener
import com.example.moviebooking.domain.viewmodels.MovieViewModel
import com.example.moviebooking.presentation.main.home.adapters.BannerAdapter
import com.example.moviebooking.presentation.main.home.adapters.NowPlayingAdapter
import com.example.moviebooking.presentation.main.home.adapters.TopRatedAdapter
import com.example.moviebooking.presentation.main.home.adapters.UpcomingAdapter
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations


class HomeScreen : BaseFragment<FragmentHomeScreenBinding, HomeRouter, MainNavigator>(
    R.layout.fragment_home_screen
) {
    override val navigator: MainNavigator by navigatorViewModel()
    private val movieViewModel: MovieViewModel by journeyViewModel()

    private lateinit var auth: FirebaseAuth

    private lateinit var nowPlayingAdapter: NowPlayingAdapter
    private lateinit var upcomingAdapter: UpcomingAdapter
    private lateinit var topRatedAdapter: TopRatedAdapter

    override fun initView(savedInstanceState: Bundle?, binding: FragmentHomeScreenBinding) {
        auth = Firebase.auth

        initialize()
        setup()
        onClickListener()
        observe()
    }

    private fun initialize() {
        movieViewModel.getLocalData()
    }

    private fun setup() {
        with(binding) {
            val welcomeMessages: Array<String> = resources.getStringArray(R.array.welcome_messages)
            val randomIndex = (welcomeMessages.indices).random()
            if (auth.currentUser?.displayName != "null") {
                hiUser.text = getString(R.string.hi, auth.currentUser?.displayName)
                welcomeText.text = welcomeMessages[randomIndex]
            } else {
                hiUser.text = welcomeMessages[randomIndex]
                welcomeText.gone()
            }

            binding.avatar.loadImage(
                source = auth.currentUser?.photoUrl,
                defaultImage = R.drawable.img_default_placeholder
            )

            searchView.clearFocus()
            searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
                if(hasFocus) {
                    activity.showKeyboardSearchView(searchView)
                } else {
                    activity.closeKeyBoard()
                }
            }

            val banners = listOf(
                Banner(R.drawable.banner1),
                Banner(R.drawable.banner2),
                Banner(R.drawable.banner3)
            )

            val bannerAdapter = BannerAdapter(banners)

            slider.apply {
                setSliderAdapter(bannerAdapter)
                setIndicatorAnimation(IndicatorAnimationType.SCALE)
                setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
            }

            nowPlayingAdapter = NowPlayingAdapter().apply {
                action = object : BaseListAdapter.Action<MovieItem> {
                    override fun click(position: Int, data: MovieItem, code: Int) {
                        router?.goToMovieDetailFromHome(
                            extras = Bundle().apply {
                                putString("movieID", data.id?.toString())
                            }
                        )
                        movieViewModel.movieId.postValue(data.id.toString())
                    }

                }
            }

            nowPlayingCarousel.apply {
                setHasFixedSize(true)
                setItemViewCacheSize(20)
                layoutManager = CarouselLayoutManager()
                adapter = nowPlayingAdapter
                CarouselSnapHelper().attachToRecyclerView(nowPlayingCarousel)
            }

            upcomingAdapter = UpcomingAdapter().apply {
                action = object : BaseListAdapter.Action<MovieItem> {
                    override fun click(position: Int, data: MovieItem, code: Int) {
                        router?.goToMovieDetailFromHome(
                            extras = Bundle().apply {
                                putString("movieID", data.id?.toString())
                            }
                        )
                        movieViewModel.movieId.postValue(data.id.toString())
                    }

                }
            }

            upcomingRV.apply {
                setHasFixedSize(true)
                setItemViewCacheSize(20)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = upcomingAdapter
                addOnScrollListener(object: EndlessOnScrollListener(layoutManager as LinearLayoutManager) {
                    override fun loadMoreItems(page: Int, totalItemsCount: Int) {
                        movieViewModel.fetchUpcomingMovies(page)
                    }
                })
            }

            topRatedAdapter = TopRatedAdapter().apply {
                action = object : BaseListAdapter.Action<MovieItem> {
                    override fun click(position: Int, data: MovieItem, code: Int) {
                        router?.goToMovieDetailFromHome(
                            extras = Bundle().apply {
                                putString("movieID", data.id?.toString())
                            }
                        )
                        movieViewModel.movieId.postValue(data.id.toString())
                    }

                }
            }

            topRatedRV.apply {
                setHasFixedSize(true)
                setItemViewCacheSize(20)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = topRatedAdapter
                addOnScrollListener(object: EndlessOnScrollListener(layoutManager as LinearLayoutManager) {
                    override fun loadMoreItems(page: Int, totalItemsCount: Int) {
                        movieViewModel.fetchTopRatedMovies(page)
                    }
                })
                val snapHelper = com.example.moviebooking.domain.common.PagerSnapHelper(
                    object : RecyclerSnapItemListener {
                        override fun onItemSnap(position: Int) {
                            overlayLayout.updateCurrentBackground(Const.tmdbImageUrlW500 + topRatedAdapter.currentList[position].posterPath)
                        }
                    }
                )
                snapHelper.attachToRecyclerView(this)
            }
        }
    }

    private fun onClickListener() {
        with(binding) {
            seeAllNowPlaying.safeClick { router?.goToSeeAll() }

            seeAllTopRated.safeClick { router?.goToSeeAll() }

            seeAllUpcoming.safeClick { router?.goToSeeAll() }
        }
    }

    private fun observe() {
        movieViewModel.nowPLayingList.observe(viewLifecycleOwner) { status ->
            when(status) {
                is ResponseStatus.Loading -> showLoading(isLoading = true)
                is ResponseStatus.Error -> {
                    //TODO
                }
                is ResponseStatus.Success -> {
                    nowPlayingAdapter.submitList(status.data.results)
                }
            }
        }

        movieViewModel.upcomingList.observe(viewLifecycleOwner) { status ->
            when(status) {
                is ResponseStatus.Loading -> {

                }

                is ResponseStatus.Error -> {

                }

                is ResponseStatus.Success -> {
                    upcomingAdapter.submitList(status.data.results)
                }
            }
        }

        movieViewModel.topRatedList.observe(viewLifecycleOwner) { status ->
            when(status) {
                is ResponseStatus.Loading -> showLoading(isLoading = true)
                is ResponseStatus.Error -> {
                    //TODO
                }
                is ResponseStatus.Success -> {
                    topRatedAdapter.submitList(status.data.results)
                    showLoading(isLoading = false)
                }
            }
        }

        movieViewModel.isLoadingNowPLaying.observe(viewLifecycleOwner) { status ->
            when(status) {
                true -> showLoading(isLoading = true)
                false -> showLoading(isLoading = false)
            }
        }
    }
}