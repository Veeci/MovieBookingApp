package com.example.baseproject.presentation

import android.app.PendingIntent
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.service.chooser.ChooserAction
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.viewbinding.ViewBinding
import com.example.baseproject.R
import com.example.baseproject.databinding.ActivityBaseBinding
import com.example.baseproject.domain.utils.LogUtils
import com.example.baseproject.domain.utils.NetworkUtil
import com.example.baseproject.domain.utils.ThemeManager
import com.example.baseproject.domain.utils.ThemeMode
import com.example.baseproject.domain.utils.toastShort
import com.example.baseproject.presentation.navigation.BackToHome
import com.example.baseproject.presentation.navigation.BaseNavigator
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
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch
import java.util.Locale

abstract class BaseActivity<V : ViewBinding, N : BaseNavigator>(private val layoutId: Int) :
    AppCompatActivity() {
    var navController: NavController? = null
    abstract val navigator: N

    private lateinit var rootView: ActivityBaseBinding
    lateinit var binding: V

    private var isDrawerEnabled = false
    private var drawerLayout: DrawerLayout? = null
    private var navigationView: NavigationView? = null

    open var statusBarHeight = 30
    open var bottomNavigationHeight = 30
    open var networkConnected = false
    open var onPermissionResult: ((requestCode: Int, permissions: Array<out String>, grantResults: IntArray, deviceId: Int) -> Unit)? =
        null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        rootView = DataBindingUtil.inflate(layoutInflater, R.layout.activity_base, null, false)
        binding = DataBindingUtil.inflate(layoutInflater, layoutId, null, false)

        val navHostFragment =
            supportFragmentManager
                .findFragmentById(R.id.container) as NavHostFragment?
        navController = navHostFragment?.navController
        setContentView(rootView.root)

        checkNetwork()
        onNavigationEvent()

        if (isDrawerEnabled) {
            setupNavigationDrawer()
        }

        handleBackPress()

        initView(savedInstanceState = savedInstanceState, binding = binding)
    }

    private fun onNavigationEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
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
                try {
                    val sendIntent = Intent(Intent.ACTION_SEND).setType("*/*")
                    val contentUri = extras?.getString(ShareFile.EXTRA)?.toUri()
                    sendIntent.apply {
                        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                        extras?.getString(ShareFile.LINK)?.let {
                            putExtra(Intent.EXTRA_TEXT, extras?.getString(ShareFile.LINK))
                        }
                        contentUri?.let {
                            data = contentUri
                            putExtra(Intent.EXTRA_STREAM, contentUri)
                        }
                    }

                    val shareIntent =
                        Intent.createChooser(sendIntent, extras?.getString(ShareFile.TITLE))

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                        val customActions =
                            arrayOf(
                                ChooserAction.Builder(
                                    Icon.createWithResource(this, R.drawable.ic_edit_base),
                                    "Custom",
                                    PendingIntent.getBroadcast(
                                        this,
                                        1,
                                        Intent(Intent.ACTION_VIEW),
                                        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_CANCEL_CURRENT,
                                    ),
                                ).build(),
                            )
                        shareIntent.putExtra(Intent.EXTRA_CHOOSER_CUSTOM_ACTIONS, customActions)
                    }
                    startActivity(shareIntent)
                } catch (e: Exception) {
                    LogUtils.log("onShareFile navigation error!", "Navigation Error: ${e.message}")
                }
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
                onPermissionResult?.invoke(
                    event.requestCode,
                    event.permissions,
                    event.grantResults,
                    event.deviceId,
                )
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

    private fun checkNetwork() {
        NetworkUtil(this).observe(this) {
            if (!it) {
                toastShort(resources.getString(R.string.offline_status))
            }
            networkConnected = it
        }
    }

    fun updateLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        config.setLayoutDirection(locale)
        applyOverrideConfiguration(config)
        recreate()
    }

    fun applyTheme(themeMode: ThemeMode) {
        when (themeMode) {
            ThemeMode.LIGHT -> setTheme(R.style.Theme_App_Light)
            ThemeMode.DARK -> setTheme(R.style.Theme_App_Dark)
            ThemeMode.CUSTOM -> setTheme(R.style.Theme_App_Custom)
        }
        ThemeManager(this).currentTheme = themeMode
        window.decorView.invalidate()
    }

    fun enableNavigationDrawable(
        enable: Boolean,
        menuResId: Int? = null,
    ) {
        isDrawerEnabled = enable
        if (enable) {
            setupNavigationDrawer(menuResId)
        }
    }

    private fun setupNavigationDrawer(menuResId: Int? = null) {
        drawerLayout = findViewById(R.id.main)
        navigationView = findViewById(R.id.navigation_menu)

        menuResId?.let { navigationView?.inflateMenu(it) }

        navigationView?.setNavigationItemSelectedListener { menuItem ->
            onNavigationItemSelected(menuItem.itemId)
            true
        }
    }

    open fun onNavigationItemSelected(menuItemId: Int) {
        when (menuItemId) {
            R.id.drawer_nav_home -> {
                LogUtils.log("NavigationDrawer", "Home selected")
            }

            R.id.drawer_nav_settings -> {
                LogUtils.log("NavigationDrawer", "Settings selected")
            }

            R.id.drawer_nav_profiles -> {
                LogUtils.log("NavigationDrawer", "Profiles selected")
            }
        }
    }

    private fun handleBackPress() {
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navigationView?.let {
                        if (isDrawerEnabled && drawerLayout?.isDrawerOpen(it) == true) {
                            drawerLayout?.closeDrawer(navigationView!!)
                        } else {
                            finish()
                        }
                    }
                }
            },
        )
    }

    fun toggleLoading(
        isLoading: Boolean,
        preventClicking: Boolean = false,
    ) {
        rootView.isLoading = isLoading
        rootView.preventClicking = preventClicking
    }

    abstract fun initView(
        savedInstanceState: Bundle?,
        binding: V,
    )
}
