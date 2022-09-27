package com.example.todo.di

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import com.example.todo.util.Utils
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        val channel1 = NotificationChannel(
            Utils.channelID,
            "Channel 1",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = getSystemService(
            NotificationManager::class.java
        )
        manager.createNotificationChannel(channel1)
    }
}