package com.example.baseproject.presentation.navigation

import androidx.core.bundle.Bundle
import com.example.baseproject.domain.viewmodel.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel

open class BaseNavigator : BaseViewModel(), BaseRouter {
    val navigation = Channel<NavigationEvent>(Channel.RENDEZVOUS)

    val receive: ReceiveChannel<NavigationEvent> get() = navigation

    fun sendEvent(navigationEvent: NavigationEvent) {
        launchCoroutine {
            navigation.send(navigationEvent)
        }
    }

    override fun onNextScreen(
        action: Int,
        extras: Bundle,
        isFinished: Boolean?,
    ) {
        launchCoroutine {
            navigation.send(
                NextScreen(
                    action,
                    extras.apply {
                        putBoolean("isFinished", isFinished ?: false)
                    },
                ),
            )
        }
    }

    override fun onPopScreen(
        action: Int?,
        inclusive: Boolean?,
        saveState: Boolean?,
    ) {
        sendEvent(
            PopScreen(
                action = action ?: -1,
                inclusive = inclusive,
                saveState = saveState,
            ),
        )
    }

    override fun backToHome(
        action: Int,
        extras: Bundle?,
    ) {
        sendEvent(BackToHome(action, extras))
    }

    override fun openDeeplink(
        action: Int,
        extras: Bundle?,
    ) {
        sendEvent(NavigateWithDeeplink(action, extras))
    }

    override fun onShareFile(extras: Bundle?) {
        sendEvent(ShareFile(extras = extras))
    }

    override fun gotoComingSoon(
        action: Int,
        extras: Bundle?,
    ) {
        sendEvent(ComingSoon(action, extras))
    }

    override fun onSessionTimeout(
        action: Int,
        extras: Bundle?,
    ) {
        sendEvent(SessionTimeout(action, extras))
    }

    override fun onNoInternet(
        action: Int,
        extras: Bundle?,
    ) {
        sendEvent(NoInternet(action, extras))
    }

    override fun onInvalidLocalTime(
        action: Int,
        extras: Bundle?,
    ) {
        sendEvent(InvalidLocalTime(action, extras))
    }

    override fun onOtherErrorDefault(
        action: Int,
        extras: Bundle?,
    ) {
        sendEvent(OtherError(action, extras))
    }

    override fun onErrorEvent(
        action: Int,
        message: String?,
    ) {
        sendEvent(ErrorEvent(action, message))
    }

    override fun onPermissionResultEvent(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int,
    ) {
        sendEvent(
            PermissionResultEvent(
                requestCode,
                permissions,
                grantResults,
                deviceId,
            ),
        )
    }

    override fun notImplemented() {
        sendEvent(NotImplementedYet)
    }

    override fun notRecognized() {
        sendEvent(NavigationEvent())
    }
}
