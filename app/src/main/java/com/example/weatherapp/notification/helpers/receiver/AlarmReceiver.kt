package com.example.weatherapp.notification.helpers.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.weatherapp.data.models.ReminderItem
import com.example.weatherapp.notification.helpers.RunnerNotifier
import com.example.weatherapp.notification.helpers.scheduler.NotificationAlarmScheduler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        var reminderItem: ReminderItem
        val id = intent!!.getIntExtra("REMINDER_ID", -1)
        val startTime = intent!!.getLongExtra("REMINDER_START_TIME", 0L)
        val lat = intent!!.getDoubleExtra("LAT",30.06263 )
        val lon = intent!!.getDoubleExtra("LON", 31.24967)
        reminderItem = ReminderItem(id, startTime,lat,lon)
        when(intent?.action){
            "Cancel" ->{
                val scheduler = NotificationAlarmScheduler(context!!)
                scheduler.cancel(reminderItem)
                val notificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.cancel(200)
            }
            "Snooze"->{
                CoroutineScope(Dispatchers.IO).launch{
                    delay(50000)
                    sendNotification(context,reminderItem)
                }
            }
            else ->{
                sendNotification(context,reminderItem)
            }
        }

    }
    fun sendNotification(context: Context?,reminderItem: ReminderItem){
        context?.let {
            ctx ->

                val notificationManager =
                    ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val runnerNotifier = RunnerNotifier(notificationManager, ctx)
                runnerNotifier.showNotification(reminderItem =reminderItem )
        }


    }

}



