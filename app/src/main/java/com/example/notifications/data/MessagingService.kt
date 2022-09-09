package com.example.notifications.data

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import com.example.notifications.MainActivity
import com.example.notifications.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val titleEvent = message.data["title"]
        val descriptionText = message.data["description"]
        val imageUrl = message.data["image"]
        if (titleEvent != null && descriptionText != null) {
            eventNotification(titleEvent, descriptionText, imageUrl)
        }
        val userName = message.data["user"]
        val description = message.data["description"]
        if (userName != null && description != null) {
            messageNotification(userName, description)
        }
    }

    private fun eventNotification(title: String, message: String, image: String?) {
        var bitmap: Bitmap? = null
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            123,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        notificationFun(title, message, bitmap, pendingIntent)

        if (image != null) {
            bitmap = Glide.with(this@MessagingService)
                .asBitmap()
                .load(image)
                .submit()
                .get()

            notificationFun(title, message, bitmap, pendingIntent)
        }
    }

    private fun notificationFun(title: String, message: String, bitmap: Bitmap?, pendingIntent: PendingIntent) {
        val notification: Notification = NotificationCompat.Builder(this, NotificationChannels.EVENTS_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setSmallIcon(R.drawable.ic_notifications)
            .setAutoCancel(true)
            .setLargeIcon(bitmap)
            .setContentIntent(pendingIntent)
            .build()

        NotificationManagerCompat.from(this)
            .notify(123, notification)
    }

    private fun messageNotification(user: String, message: String) {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            123,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(this, NotificationChannels.MESSAGE_CHANNEL_ID)
            .setContentTitle(user)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.ic_message)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        NotificationManagerCompat.from(this)
            .notify(322, notification)
    }
}