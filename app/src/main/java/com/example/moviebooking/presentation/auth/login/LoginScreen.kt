package com.example.moviebooking.presentation.auth.login

import android.os.Bundle
import com.example.baseproject.domain.utils.LogUtils
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.domain.utils.toastShort
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentAuthBinding
import com.example.moviebooking.presentation.auth.login.adapter.SliderAdapter
import com.example.moviebooking.presentation.auth.login.adapter.SliderItem
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

class LoginScreen : BaseFragment<FragmentAuthBinding, LoginRouter, MainNavigator>(
    R.layout.fragment_auth
) {
    override val navigator: MainNavigator by navigatorViewModel()

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()

        if(auth.currentUser != null) {
            router?.goToMainScreen()
        }
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentAuthBinding) {
        setup()
    }

    private fun setup() {
        val sliderItems = listOf(
            SliderItem("https://image.tmdb.org/t/p/w500/vxMeUZ46wMYijcPSYxPCrD1ZHgx.jpg", "Welcome to Movie Booking!"),
            SliderItem("https://image.tmdb.org/t/p/w500/1QdXdRYfktUSONkl1oD5gc6Be0s.jpg", "Discover New Movies"),
            SliderItem("https://image.tmdb.org/t/p/w500/xbZQ3fDl0y5mt0ARwfeyrgQ4JTw.jpg", "Book Tickets Quickly"),
        )

        val sliderAdapter = SliderAdapter(sliderItems)

        binding.slider.apply {
            setSliderAdapter(sliderAdapter)
            setIndicatorAnimation(IndicatorAnimationType.WORM)
            setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION)
            autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
            scrollTimeInSec = 3
            startAutoCycle()
        }

        with(binding) {
            loginBtn.safeClick {
                emailPasswordSignIn(binding.usernameInput.text.toString(), binding.passwordInput.text.toString())
            }
            googleLoginBtn.safeClick {

            }
            facebookLoginBtn.safeClick {

            }
            toSignUpBtn.safeClick {
                router?.goToSignUpScreen()
            }
        }
    }

    private fun emailPasswordSignIn(email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()) {
            activity.toastShort("Please fill in your email and password!")
            return
        }
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if(task.isSuccessful) {
                    val user = auth.currentUser
                    //todo: save user's cache
                    router?.goToMainScreen()
                } else {
                    activity.toastShort("Invalid email or password. Please try again!")
                    LogUtils.log("Auth", "Email password sign in error: ${task.exception}")
                }
            }
    }
}