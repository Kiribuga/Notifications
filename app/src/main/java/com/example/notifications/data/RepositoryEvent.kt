package com.example.notifications.data

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import com.example.notifications.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryEvent(
    private val context: Context
) {
    suspend fun eventNotification(title: String, message: String, image: String?) {
        withContext(Dispatchers.IO) {
            val notification =
                NotificationCompat.Builder(context, NotificationChannels.EVENTS_CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setSmallIcon(R.drawable.ic_notifications)
                    .build()
            NotificationManagerCompat.from(context)
                .notify(123, notification)

            if (image != null) {
                loadBitmap(title, message, image)
            }
        }
    }

    private suspend fun loadBitmap(title: String, message: String, image: String?) {
        withContext(Dispatchers.IO) {
            val bitmap = Glide.with(context)
                .asBitmap()
                .load(image)
                .submit()
                .get()

            val notification =
                NotificationCompat.Builder(context, NotificationChannels.EVENTS_CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setSmallIcon(R.drawable.ic_notifications)
                    .setLargeIcon(bitmap)
                    .build()
            NotificationManagerCompat.from(context)
                .notify(123, notification)
        }
    }
}