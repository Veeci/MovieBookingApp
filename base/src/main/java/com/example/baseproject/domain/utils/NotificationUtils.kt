package com.example.baseproject.domain.utils

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object NotificationUtils {
    private const val DEFAULT_CHANNEL_ID = "default_channel"
    private const val DEFAULT_CHANNEL_NAME = "General Notifications"

    /**
     * Creates a notification channel (Required for Android 8.0 and above).
     *
     * @param context The application context.
     * @param channelId The unique ID for the channel.
     * @param channelName The user-visible name of the channel.
     * @param importance The importance level of the channel (e.g., NotificationManager.IMPORTANCE_DEFAULT).
     * @param enableLights Whether to enable lights for notifications in this channel.
     * @param enableVibration Whether to enable vibration for notifications in this channel.
     */
    fun createNotificationChannel(
        context: Context,
        channelId: String = DEFAULT_CHANNEL_ID,
        channelName: String = DEFAULT_CHANNEL_NAME,
        importance: Int = NotificationManager.IMPORTANCE_DEFAULT,
        enableLights: Boolean = true,
        enableVibration: Boolean = true,
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, channelName, importance).apply {
                    lightColor = Color.BLUE
                    enableLights(enableLights)
                    enableVibration(enableVibration)
                    description = "Notifications for $channelName"
                }
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    /**
     * Builds and shows a notification.
     *
     * @param context The application context.
     * @param channelId The ID of the notification channel.
     * @param title The title of the notification.
     * @param message The body text of the notification.
     * @param notificationId A unique ID for the notification.
     * @param smallIconResId The resource ID for the notification's small icon.
     * @param autoCancel Whether the notification should be dismissed when clicked.
     * @param priority The priority level of the notification (e.g., NotificationCompat.PRIORITY_DEFAULT).
     */
    fun showNotification(
        context: Context,
        channelId: String = DEFAULT_CHANNEL_ID,
        title: String,
        message: String,
        notificationId: Int,
        smallIconResId: Int,
        autoCancel: Boolean = true,
        priority: Int = NotificationCompat.PRIORITY_DEFAULT,
    ) {
        val builder =
            NotificationCompat.Builder(context, channelId)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(smallIconResId)
                .setPriority(priority)
                .setAutoCancel(autoCancel)
                .setDefaults(Notification.DEFAULT_ALL) // Sound, vibration, lights
                .setStyle(NotificationCompat.BigTextStyle().bigText(message)) // Expands message if lengthy

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS,
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        NotificationManagerCompat.from(context).notify(notificationId, builder.build())
    }

    /**
     * Cancels a notification by its ID.
     *
     * @param context The application context.
     * @param notificationId The unique ID of the notification to cancel.
     */
    fun cancelNotification(
        context: Context,
        notificationId: Int,
    ) {
        NotificationManagerCompat.from(context).cancel(notificationId)
    }

    /**
     * Cancels all notifications.
     *
     * @param context The application context.
     */
    fun cancelAllNotifications(context: Context) {
        NotificationManagerCompat.from(context).cancelAll()
    }
}

/**
 * Example of usage:
 * - Create a Notification Channel: call this method once, typically in your Application class or when initializing notifications
 * NotificationUtils.createNotificationChannel(
 *     context = applicationContext,
 *     channelId = "important_updates",
 *     channelName = "Important Updates",
 *     importance = NotificationManager.IMPORTANCE_HIGH
 * )
 *
 * - Show notification with a title, message and icon:
 * NotificationUtils.showNotification(
 *     context = applicationContext,
 *     channelId = "important_updates",
 *     title = "New Update Available",
 *     message = "Check out the latest features in the app!",
 *     notificationId = 1,
 *     smallIconResId = R.drawable.ic_notification
 * )
 *
 * - Cancel a specific notification: NotificationUtils.cancelNotification(context = applicationContext, notificationId = 1)
 *
 * - Remove all active notifications: NotificationUtils.cancelAllNotifications(context = applicationContext)
 */
