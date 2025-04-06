package com.example.weatherapp.notification.helpers.scheduler

import android.app.PendingIntent
import com.example.weatherapp.data.models.ReminderItem

interface AlarmScheduler {
    fun createPendingIntent(reminderItem: ReminderItem): PendingIntent

    fun schedule(reminderItem: ReminderItem)

    fun cancel(reminderItem: ReminderItem)
}