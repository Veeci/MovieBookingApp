package com.example.moviebooking.presentation.main.home

import android.os.Bundle
import android.view.DragEvent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseproject.domain.utils.EndlessOnScrollListener
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.presentation.BaseFragment
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentHomeScreenBinding
import com.example.moviebooking.domain.viewmodels.MovieViewModel
import com.example.moviebooking.presentation.main.home.adapters.NowPlayingAdapter
import com.example.moviebooking.presentation.main.home.adapters.UpcomingAdapter
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class HomeScreen : BaseFragment<FragmentHomeScreenBinding, HomeRouter, MainNavigator>(
    R.layout.fragment_home_screen
) {
    override val navigator: MainNavigator by navigatorViewModel()
    private val movieViewModel: MovieViewModel by journeyViewModel()

    private lateinit var auth: FirebaseAuth

    private var dX: Float = 0.0f
    private var dY: Float = 0.0f

    private val nowPlayingAdapter = NowPlayingAdapter()
    private val upcomingAdapter = UpcomingAdapter()

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
            hiUser.text = getString(R.string.hi, auth.currentUser?.displayName)

            val welcomeMessages: Array<String> = resources.getStringArray(R.array.welcome_messages)
            val randomIndex = (welcomeMessages.indices).random()
            welcomeText.text = welcomeMessages[randomIndex]

            binding.avatar.loadImage(
                source = auth.currentUser?.photoUrl,
                defaultImage = R.drawable.img_default_placeholder
            )

            nowPlayingCarousel.apply {
                setHasFixedSize(true)
                layoutManager = CarouselLayoutManager()
                adapter = nowPlayingAdapter
                CarouselSnapHelper().attachToRecyclerView(nowPlayingCarousel)
            }

            upcomingRV.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = upcomingAdapter
                addOnScrollListener(object: EndlessOnScrollListener(layoutManager as LinearLayoutManager) {
                    override fun loadMoreItems(page: Int, totalItemsCount: Int) {
                        movieViewModel.fetchUpcomingMovies(page)
                    }
                })
            }
        }

        setupMovableFab()
    }

    private fun onClickListener() {
        binding.chatbotBtn.safeClick {

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

        movieViewModel.isLoadingNowPLaying.observe(viewLifecycleOwner) { status ->
            when(status) {
                true -> showLoading(isLoading = true)
                false -> showLoading(isLoading = false)
            }
        }
    }

    private fun setupMovableFab() {
        binding.root.setOnDragListener { v, event ->
            when(event.action) {
                DragEvent.ACTION_DRAG_LOCATION -> {
                    dX = event.x
                    dY = event.y
                }

                DragEvent.ACTION_DRAG_ENDED -> {
                    binding.chatbotBtn.x = dX - binding.chatbotBtn.width / 2
                    binding.chatbotBtn.y = dY - binding.chatbotBtn.height / 2
                }
            }

            true
        }

        binding.chatbotBtn.setOnLongClickListener { v ->
            val shadow = View.DragShadowBuilder(binding.chatbotBtn)
            v.startDragAndDrop(null, shadow, null, View.DRAG_FLAG_GLOBAL)
            true
        }
    }
}