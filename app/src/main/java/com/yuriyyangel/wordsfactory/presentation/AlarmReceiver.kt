package com.yuriyyangel.wordsfactory.presentation

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.yuriyyangel.wordsfactory.R

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notificationHelper = NotificationHelper(context)
        val notification = notificationHelper.createNotification()

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NotificationHelper.NOTIFICATION_ID, notification)
    }
}

class NotificationHelper(private val context: Context) {

    companion object {
        const val CHANNEL_ID = "training_reminder"
        const val NOTIFICATION_ID = 1
    }

    fun createNotification(): Notification {
        createNotificationChannel()

        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Training Reminder")
            .setContentText("You forgot to practice today! Let's do it now!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
    }


    private fun createNotificationChannel() {
        val name = "Training Reminder"
        val descriptionText = "You forgot to practice today!"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }


}

