package com.example.notifications.data

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notifications.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryMessage(
    private val context: Context
) {
    suspend fun messageNotification(userId: Long, userName: String, message: String) {
        withContext(Dispatchers.IO) {
            val notification =
                NotificationCompat.Builder(context, NotificationChannels.MESSAGE_CHANNEL_ID)
                    .setContentTitle(userName)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setSmallIcon(R.drawable.ic_message)
                    .build()

            NotificationManagerCompat.from(context)
                .notify(userId.toInt(), notification)
        }
    }
}