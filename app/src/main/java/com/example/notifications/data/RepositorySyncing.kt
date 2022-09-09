package com.example.notifications.data

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notifications.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class RepositorySyncing(
    private val context: Context
) {

    suspend fun progressNotification() {
        val notificationBuilder =
            NotificationCompat.Builder(context, NotificationChannels.EVENTS_CHANNEL_ID)
                .setContentTitle("Synchronisation")
                .setContentText("Please wait")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setSmallIcon(R.drawable.ic_notifications)

        val maxProgress = 20
        withContext(Dispatchers.IO) {
            (0 until maxProgress).forEach { progress ->
                val notification = notificationBuilder
                    .setProgress(maxProgress, progress, false)
                    .build()

                NotificationManagerCompat.from(context)
                    .notify(111, notification)

                delay(500)
            }

            val finalNotification = notificationBuilder
                .setContentText("Synchronisation completed!")
                .setProgress(0, 0, false)
                .build()

            NotificationManagerCompat.from(context)
                .notify(111, finalNotification)
            delay(1000)

            NotificationManagerCompat.from(context)
                .cancel(111)
        }
    }
}