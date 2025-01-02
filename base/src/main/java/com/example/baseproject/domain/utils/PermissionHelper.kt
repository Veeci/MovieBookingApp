package com.example.baseproject.domain.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

abstract class BasePermissionHelper {
    protected abstract fun permissions(): Array<String>

    companion object {
        private const val PERMISSION_ALL = 1001
    }

    open fun hasPermissions(context: Context): Boolean =
        permissions().all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }

    open fun requestPermissions(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            permissions(),
            PERMISSION_ALL,
        )
    }
}

sealed class PermissionHelper {
    companion object {
        private const val KEY_APP_PACKAGE_NAME = "app_package"
        private const val KEY_APP_UID = "app_uid"
    }

    object Camera : BasePermissionHelper() {
        override fun permissions(): Array<String> {
            return arrayOf(Manifest.permission.CAMERA)
        }
    }

    object ReadContact : BasePermissionHelper() {
        override fun permissions(): Array<String> {
            return arrayOf(Manifest.permission.READ_CONTACTS)
        }
    }

    object CallPhone : BasePermissionHelper() {
        override fun permissions(): Array<String> {
            return arrayOf(Manifest.permission.CALL_PHONE)
        }
    }

    object ReadExternalStorage : BasePermissionHelper() {
        override fun permissions(): Array<String> {
            return arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    object WriteExternalStorage : BasePermissionHelper() {
        override fun permissions(): Array<String> {
            return arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }

    object Location : BasePermissionHelper() {
        override fun permissions(): Array<String> {
            return arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    object RecordAudio : BasePermissionHelper() {
        override fun permissions(): Array<String> {
            return arrayOf(Manifest.permission.RECORD_AUDIO)
        }
    }

    object WakeLock : BasePermissionHelper() {
        override fun permissions(): Array<String> {
            return arrayOf(Manifest.permission.WAKE_LOCK)
        }
    }

    object PostNotification : BasePermissionHelper() {
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        override fun permissions(): Array<String> {
            return arrayOf(Manifest.permission.POST_NOTIFICATIONS)
        }

        override fun hasPermissions(context: Context): Boolean {
            return NotificationManagerCompat.from(context)
                .areNotificationsEnabled()
        }

        override fun requestPermissions(activity: Activity) {
            try {
                val intent = Intent()
                if (!NotificationManagerCompat.from(activity)
                        .areNotificationsEnabled()
                ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
                        intent.putExtra(Settings.EXTRA_APP_PACKAGE, activity.packageName)
                    } else {
                        intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
                    }
                }
                intent.putExtra(KEY_APP_PACKAGE_NAME, activity.packageName)
                intent.putExtra(KEY_APP_UID, activity.applicationInfo.uid)
                activity.startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    object Bluetooth : BasePermissionHelper() {
        override fun permissions(): Array<String> {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                arrayOf(
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT,
                )
            } else {
                arrayOf(Manifest.permission.BLUETOOTH)
            }
        }
    }

    object BodySensors : BasePermissionHelper() {
        override fun permissions(): Array<String> {
            return arrayOf(Manifest.permission.BODY_SENSORS)
        }
    }

    object ActivityRecognition : BasePermissionHelper() {
        override fun permissions(): Array<String> {
            return arrayOf(Manifest.permission.ACTIVITY_RECOGNITION)
        }
    }

    object NFC : BasePermissionHelper() {
        override fun permissions(): Array<String> {
            return arrayOf(Manifest.permission.NFC)
        }
    }

    object Biometric : BasePermissionHelper() {
        override fun permissions(): Array<String> {
            return arrayOf(Manifest.permission.USE_BIOMETRIC)
        }
    }

    object AllPermission : BasePermissionHelper() {
        override fun permissions() =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arrayOf(
                    Manifest.permission.POST_NOTIFICATIONS,
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.WAKE_LOCK,
                )
            } else {
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.WAKE_LOCK,
                )
            }

        override fun hasPermissions(context: Context): Boolean {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                super.hasPermissions(context) &&
                    NotificationManagerCompat.from(context)
                        .areNotificationsEnabled()
            } else {
                super.hasPermissions(context)
            }
        }

        override fun requestPermissions(activity: Activity) {
            super.requestPermissions(activity)
            try {
                val intent = Intent()
                if (!NotificationManagerCompat.from(activity)
                        .areNotificationsEnabled()
                ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
                        intent.putExtra(Settings.EXTRA_APP_PACKAGE, activity.packageName)
                    } else {
                        intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
                    }
                }
                intent.putExtra(KEY_APP_PACKAGE_NAME, activity.packageName)
                intent.putExtra(KEY_APP_UID, activity.applicationInfo.uid)
                activity.startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

/**
 * Example of usage:
 * - Check for permission:
 * if (PermissionHelper.Camera.hasPermissions(context)) {
 *     // Permission is granted, proceed with camera functionality
 * } else {
 *     // Permission is not granted, request it
 *     PermissionHelper.Camera.requestPermissions(activity)
 * }
 *
 * - Request permission: PermissionHelper.Location.requestPermissions(activity)
 *
 * - Handle with permission results:
 * override fun onRequestPermissionsResult(
 *     requestCode: Int,
 *     permissions: Array<out String>,
 *     grantResults: IntArray
 * ) {
 *     super.onRequestPermissionsResult(requestCode, permissions, grantResults)
 *     if (requestCode == BasePermissionHelper.PERMISSION_ALL) {
 *         if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
 *             // Permission granted, proceed with functionality
 *         } else {
 *             // Permission denied, handle accordingly
 *         }
 *     }
 * }
 *
 * - And request multiple permissions: PermissionHelper.AllPermission.requestPermissions(activity)
 **/
