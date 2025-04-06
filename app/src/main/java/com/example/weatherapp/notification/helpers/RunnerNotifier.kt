package com.example.weatherapp.notification.helpers

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
import com.example.weatherapp.MainActivity
import com.example.weatherapp.data.models.ReminderItem
import com.example.weatherapp.notification.helpers.receiver.AlarmReceiver

/*
class RunnerNotifier(
    private val notificationManager: NotificationManager,
    private val context: Context,
) : Notifier(notificationManager,context) {

    override val notificationChannelId: String = "runner_channel_id"
    override val notificationChannelName: String = "Running Notification"
    override val notificationId: Int = 100

    override fun buildNotification( reminderItem: ReminderItem): Notification {
        val fullScreenIntent = Intent(context, MainActivity::class.java).apply {
            putExtra("DESTINATION", "weather_screen")
            putExtra("LAT", reminderItem.latitude)
            putExtra("LON", reminderItem.longitude)
        }
        val fullScreenPendingIntent = PendingIntent.getActivity(
            context, 0, fullScreenIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val cancelIntent = Intent(context, AlarmReceiver::class.java).apply {
            action="Cancel"
            putExtra("REMINDER_ID", reminderItem.id)
            putExtra("REMINDER_TIME", reminderItem.startTime)

        }

        val cancelPendingIntent = PendingIntent.getBroadcast(
            context, reminderItem.id, cancelIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val snoozeIntent = Intent(context, AlarmReceiver::class.java).apply {
            action="Snooze"
            putExtra("REMINDER_ID", reminderItem.id)
            putExtra("REMINDER_TIME", reminderItem.startTime)
        }

        val snoozePendingIntent = PendingIntent.getBroadcast(
            context, reminderItem.id, snoozeIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val soundUri = Uri.parse("${ContentResolver.SCHEME_ANDROID_RESOURCE}://${context.packageName}/raw/cinematic")
        return NotificationCompat.Builder(context, notificationChannelId)
            .setContentTitle(getNotificationTitle())
            .setContentText(getNotificationMessage())
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(Notification.DEFAULT_ALL)
            .setSound(soundUri)
            .setAutoCancel(true)
            .addAction(android.R.drawable.ic_menu_info_details,"Snooze",snoozePendingIntent)
            .addAction(android.R.drawable.ic_menu_info_details,"Cancel",cancelPendingIntent)
            .setFullScreenIntent(fullScreenPendingIntent, true)
            .build()
    }


    override fun getNotificationTitle(): String {
        return "Weather App"
    }

    override fun getNotificationMessage(): String {
        return "Hello , HaHaHaHaHaHa!"
    }

}*/

class RunnerNotifier(
    private val notificationManager: NotificationManager,
    private val context: Context
) : Notifier(notificationManager,context) {

    override val notificationChannelId: String = "runner_channel_id"
    override val notificationChannelName: String = "Running Notification"
    override val notificationId: Int = 200

    override fun buildNotification(): Notification {
        val fullScreenIntent = Intent(context, MainActivity::class.java).apply {

        }
        val fullScreenPendingIntent = PendingIntent.getActivity(
            context, 0, fullScreenIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val soundUri = Uri.parse("${ContentResolver.SCHEME_ANDROID_RESOURCE}://${context.packageName}/raw/sound")
        return NotificationCompat.Builder(context, notificationChannelId)
            .setContentTitle(getNotificationTitle())
            .setContentText(getNotificationMessage())
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(Notification.DEFAULT_ALL)
            .setSound(soundUri)
            .setAutoCancel(true)
            .setFullScreenIntent(fullScreenPendingIntent, true)
            .build()
    }

    override fun getNotificationTitle(): String {
        return "Weather App"
    }

    override fun getNotificationMessage(): String {
        return "Hello"
    }
}