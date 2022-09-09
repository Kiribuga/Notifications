package com.example.notifications.data

import android.app.Application
import android.util.Log

class NotificationApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("NotificationApp", "Create channels")
        NotificationChannels.create(this)
    }
}