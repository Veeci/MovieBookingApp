package com.example.baseproject.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.viewbinding.ViewBinding
import com.example.baseproject.R
import com.example.baseproject.domain.utils.LogUtils
import com.example.baseproject.domain.utils.screenViewModel
import com.example.baseproject.domain.viewmodel.BaseViewModel
import com.example.baseproject.domain.viewmodel.ErrorState
import com.example.baseproject.presentation.navigation.BackToHome
import com.example.baseproject.presentation.navigation.BaseNavigator
import com.example.baseproject.presentation.navigation.BaseRouter
import com.example.baseproject.presentation.navigation.ComingSoon
import com.example.baseproject.presentation.navigation.InvalidLocalTime
import com.example.baseproject.presentation.navigation.NavigateWithDeeplink
import com.example.baseproject.presentation.navigation.NavigationEvent
import com.example.baseproject.presentation.navigation.NextScreen
import com.example.baseproject.presentation.navigation.NoInternet
import com.example.baseproject.presentation.navigation.NotImplementedYet
import com.example.baseproject.presentation.navigation.NotImplementedYet.extras
import com.example.baseproject.presentation.navigation.PermissionResultEvent
import com.example.baseproject.presentation.navigation.PopScreen
import com.example.baseproject.presentation.navigation.SessionTimeout
import com.example.baseproject.presentation.navigation.ShareFile
import kotlinx.coroutines.launch

abstract class BaseFragment<V : ViewBinding, Router : BaseRouter, out N : BaseNavigator>(
    private val layoutId: Int = 0,
) : Fragment() {
    protected lateinit var binding: V
    abstract val navigator: N
    var navController: NavController? = null
    protected var router: Router? = null
    protected lateinit var activity: BaseActivity<*, *, *>

    open val viewModel: BaseViewModel by screenViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, layoutId, container, false)
        activity = requireActivity() as BaseActivity<*, *, *>

        return binding.root
    }

    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        try {
            (navigator as? Router?)?.let {
                router = it
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        initView(savedInstanceState = savedInstanceState, binding = binding)

        val navHostFragment =
            childFragmentManager
                .findFragmentById(R.id.mainView) as NavHostFragment?
        navController = navHostFragment?.navController
        onNavigationEvent()

        backPressHandle()
        errorObserver()
        permissionRegister()
    }

    abstract fun initView(
        savedInstanceState: Bundle?,
        binding: V,
    )

    private fun onNavigationEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                for (event in navigator.receive) {
                    onNavigationEvent(event = event)
                }
            }
        }
    }

    open fun onNavigationEvent(event: NavigationEvent) {
        when (event) {
            is NextScreen -> {
                try {
                    navController?.navigate(
                        resId = event.action,
                        args = event.extras,
                        navOptions =
                            navOptions {
                                anim {
                                    enter = event.enterAnim ?: android.R.anim.fade_in
                                    exit = event.exitAnim ?: android.R.anim.fade_out
                                    popEnter = event.popEnterAnim ?: android.R.anim.fade_in
                                    popExit = event.popExitAnim ?: android.R.anim.fade_out
                                }
                                if (event.extras.getBoolean("isFinished", false)) {
                                    launchSingleTop = true
                                    popUpTo(resources.getString(R.string.base_project)) {
                                        inclusive = true
                                        saveState = true
                                    }
                                }
                            },
                    )
                } catch (e: Exception) {
                    LogUtils.log("onNextScreen navigation error!", "Navigation Error: ${e.message}")
                }
            }

            is PopScreen -> {
                try {
                    navController?.let { navController ->
                        if (navController.previousBackStackEntry != null &&
                            navController.currentBackStackEntry != null
                        ) {
                            navController.popBackStack(
                                destinationId = event.action,
                                inclusive = event.inclusive != false,
                                saveState = event.saveState == false,
                            )
                        } else {
                            LogUtils.log(
                                "onPopScreen navigation error!",
                                "Navigation Error: BackStack is empty",
                            )
                        }
                    } ?: run {
                        navController?.popBackStack()
                    }
                } catch (e: Exception) {
                    LogUtils.log("onPopScreen navigation error!", "Navigation Error: ${e.message}")
                }
            }

            is BackToHome -> {
                try {
                    // todo
                } catch (e: Exception) {
                    LogUtils.log("onBackToHome navigation error!", "Navigation Error: ${e.message}")
                }
            }

            is NavigateWithDeeplink -> {
                try {
                    // todo
                } catch (e: Exception) {
                    LogUtils.log(
                        "onNavigateWithDeeplink navigation error!",
                        "Navigation Error: ${e.message}",
                    )
                }
            }

            is ShareFile -> {
//                try {
//                    val sendIntent = Intent(Intent.ACTION_SEND).setType("*/*")
//                    val contentUri = extras?.getString(ShareFile.EXTRA)?.toUri()
//                    sendIntent.apply {
//                        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
//                        extras?.getString(ShareFile.LINK)?.let {
//                            putExtra(Intent.EXTRA_TEXT, extras?.getString(ShareFile.LINK))
//                        }
//                        contentUri?.let {
//                            data = contentUri
//                            putExtra(Intent.EXTRA_STREAM, contentUri)
//                        }
//                    }
//
//                    val shareIntent =
//                        Intent.createChooser(sendIntent, extras?.getString(ShareFile.TITLE))
//
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
//                        val customActions =
//                            arrayOf(
//                                ChooserAction.Builder(
//                                    Icon.createWithResource(this, R.drawable.ic_edit_base),
//                                    "Custom",
//                                    PendingIntent.getBroadcast(
//                                        this,
//                                        1,
//                                        Intent(Intent.ACTION_VIEW),
//                                        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_CANCEL_CURRENT,
//                                    ),
//                                ).build(),
//                            )
//                        shareIntent.putExtra(Intent.EXTRA_CHOOSER_CUSTOM_ACTIONS, customActions)
//                    }
//                    startActivity(shareIntent)
//                } catch (e: Exception) {
//                    LogUtils.log("onShareFile navigation error!", "Navigation Error: ${e.message}")
//                }
            }

            is ComingSoon -> {
                try {
                    // todo
                } catch (e: Exception) {
                    LogUtils.log("onComingSoon navigation error!", "Navigation Error: ${e.message}")
                }
            }

            is SessionTimeout -> {
                try {
                    // todo
                } catch (e: Exception) {
                    LogUtils.log(
                        "onSessionTimeout navigation error!",
                        "Navigation Error: ${e.message}",
                    )
                }
            }

            is NoInternet -> {
                try {
                    // todo
                } catch (e: Exception) {
                    LogUtils.log("onNoInternet navigation error!", "Navigation Error: ${e.message}")
                }
            }

            is InvalidLocalTime -> {
                try {
                    // todo
                } catch (e: Exception) {
                    LogUtils.log(
                        "onInvalidLocalTime navigation error!",
                        "Navigation Error: ${e.message}",
                    )
                }
            }

            is PermissionResultEvent -> {
//                onPermissionResult?.invoke(
//                    event.requestCode,
//                    event.permissions,
//                    event.grantResults,
//                    event.deviceId,
//                )
            }

            is NotImplementedYet -> {
                try {
                    // todo
                } catch (e: Exception) {
                    LogUtils.log(
                        "onNotImplementedYet navigation error!",
                        "Navigation Error: ${e.message}",
                    )
                }
            }
        }
    }

    protected fun navigateSafely(action: Int) {
        val navController = activity.navController
        if (navController != null && navController.currentDestination?.getAction(action) != null) {
            navController.navigate(action)
        } else {
            navigator.onErrorEvent(
                message = "Destination not found",
                action = 0,
            )
        }
    }

    open fun onBackPressed(): Boolean = false

    private fun backPressHandle() {
        activity.onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (!onBackPressed()) {
                activity.onBackPressedDispatcher.onBackPressed()
            } else {
                isEnabled = false
            }
        }
    }

    private fun errorObserver() {
        viewModel.errorState.observe(viewLifecycleOwner) { errorState ->
            if (!handleErrorState(errorState)) {
                when (errorState) {
                    is ErrorState.GenericError -> {
                        navigator.onErrorEvent(
                            message = errorState.message,
                            action = 0,
                        )
                    }

                    is ErrorState.NetworkError -> {
                        navigator.onErrorEvent(
                            message = errorState.exception.message,
                            action = 0,
                        )
                    }
                }
            }
        }
    }

    open fun handleErrorState(errorState: ErrorState): Boolean = false

    private fun permissionRegister() {
        activity.onPermissionResult = { requestCode, permissions, grantResults, deviceId ->
            onPermissionResult(requestCode, permissions, grantResults, deviceId)
        }
    }

    open fun onPermissionResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int,
    ) = Unit

    fun showLoading(
        isLoading: Boolean,
        preventClicking: Boolean = false,
    ) {
        activity.toggleLoading(isLoading, preventClicking)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.errorState.removeObservers(viewLifecycleOwner)
    }
}

/**
 * Example in use:
 * -Handle the back press: for instance in the way you want to handle the back press to confirm before exiting the app:
 * override fun onBackPressed(): Boolean {
 *         AlertDialog.Builder(requireContext())
 *             .setTitle("Exit App")
 *             .setMessage("Are you sure you want to exit?")
 *             .setPositiveButton("Yes") { _, _ ->
 *                 requireActivity().finish()
 *             }
 *             .setNegativeButton("No", null)
 *             .show()
 *         return true
 * }
 */
