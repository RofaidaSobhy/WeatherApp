package com.example.weatherapp.notification.helpers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentResolver
import android.content.Context
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weatherapp.data.models.ReminderItem


abstract class Notifier(
    //all types of notification will inherit from it
    private val notificationManager: NotificationManager,
    private val context : Context
) {
    abstract val notificationChannelId: String
    abstract val notificationChannelName: String
    abstract val notificationId: Int

    //if android 8 or higher first create channel
    fun showNotification(reminderItem: ReminderItem) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = createNotificationChannel()
            notificationManager.createNotificationChannel(channel)
        }
        val notification = buildNotification(reminderItem)
        notificationManager.notify(
            notificationId,
            notification
        )
    }
    //create channel method
    @RequiresApi(Build.VERSION_CODES.O)
    open fun createNotificationChannel(): NotificationChannel {
        val soundUri = Uri.parse("${ContentResolver.SCHEME_ANDROID_RESOURCE}://${context.packageName}/raw/cinematic")

        return NotificationChannel(
            notificationChannelId,
            notificationChannelName,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Notifications for running reminders"
            setShowBadge(true)
            enableLights(true)
            enableVibration(true)
            setSound(soundUri, AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build())
        }
    }

    abstract fun buildNotification(reminderItem: ReminderItem): Notification

    protected abstract fun getNotificationTitle(): String

    protected abstract fun getNotificationMessage(): String
}