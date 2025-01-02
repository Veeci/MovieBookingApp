package com.example.baseproject.presentation.navigation

import android.os.Bundle

open class NavigationEvent {
    open val action: Int = 0
    open val extras: Bundle? = null
    open val enterAnim: Int? = null
    open val exitAnim: Int? = null
    open val popEnterAnim: Int? = null
    open val popExitAnim: Int? = null
}

data class NextScreen(
    override val action: Int,
    override val extras: Bundle = Bundle(),
    override val enterAnim: Int? = null,
    override val exitAnim: Int? = null,
    override val popEnterAnim: Int? = null,
    override val popExitAnim: Int? = null,
) : NavigationEvent()

data class PopScreen(
    override val action: Int = -1,
    val inclusive: Boolean? = null,
    val saveState: Boolean? = null,
    override val extras: Bundle? = null,
    override val enterAnim: Int = android.R.anim.slide_in_left,
    override val exitAnim: Int = android.R.anim.slide_out_right,
    override val popEnterAnim: Int = android.R.anim.fade_in,
    override val popExitAnim: Int = android.R.anim.fade_out,
) : NavigationEvent()

data class BackToHome(
    override val action: Int = 0,
    override val extras: Bundle? = null,
) : NavigationEvent()

data class NavigateWithDeeplink(
    override val action: Int = 0,
    override val extras: Bundle? = null,
) : NavigationEvent()

data class ShareFile(
    override val action: Int = 0,
    override val extras: Bundle? = null,
) : NavigationEvent() {
    companion object {
        const val TITLE = "title"
        const val EXTRA = "extra"
        const val LINK = "link"
    }
}

data class ComingSoon(
    override val action: Int = 0,
    override val extras: Bundle? = null,
) : NavigationEvent()

data class SessionTimeout(
    override val action: Int = 0,
    override val extras: Bundle? = null,
) : NavigationEvent()

data class NoInternet(
    override val action: Int = 0,
    override val extras: Bundle? = null,
) : NavigationEvent()

data class InvalidLocalTime(
    override val action: Int = 0,
    override val extras: Bundle? = null,
) : NavigationEvent()

data class OtherError(
    override val action: Int = 0,
    override val extras: Bundle? = null,
) : NavigationEvent()

data class ErrorEvent(
    override val action: Int = 0,
    val message: String? = null,
) : NavigationEvent()

class PermissionResultEvent(
    val requestCode: Int,
    val permissions: Array<out String>,
    val grantResults: IntArray,
    val deviceId: Int,
) : NavigationEvent()

object NotImplementedYet : NavigationEvent()
