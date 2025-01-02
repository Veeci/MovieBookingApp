package com.example.baseproject.presentation.navigation

import android.os.Bundle

interface BaseRouter {
    fun onNextScreen(
        action: Int,
        extras: Bundle = Bundle(),
        isFinished: Boolean?,
    )

    fun onPopScreen(
        action: Int? = null,
        inclusive: Boolean? = null,
        saveState: Boolean? = null,
    )

    fun backToHome(
        action: Int,
        extras: Bundle?,
    )

    fun openDeeplink(
        action: Int,
        extras: Bundle?,
    )

    fun onShareFile(extras: Bundle?)

    fun gotoComingSoon(
        action: Int,
        extras: Bundle?,
    )

    fun onSessionTimeout(
        action: Int,
        extras: Bundle?,
    )

    fun onNoInternet(
        action: Int,
        extras: Bundle?,
    )

    fun onInvalidLocalTime(
        action: Int,
        extras: Bundle?,
    )

    fun onOtherErrorDefault(
        action: Int,
        extras: Bundle?,
    )

    fun onErrorEvent(
        action: Int,
        message: String?,
    )

    fun onPermissionResultEvent(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int,
    )

    fun notImplemented()

    fun notRecognized()
}
