package com.panov.proxy.utils

import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import com.panov.proxy.R

object NotificationManager {
    const val FOREGROUND_SERVICE_CHANNEL_ID = "channel_foreground_service"

    fun createChannelForForegroundService(context: Context) {
        val manager = context.getSystemService(android.app.NotificationManager::class.java)
        val channel = NotificationChannel(
            FOREGROUND_SERVICE_CHANNEL_ID,
            context.getString(R.string.channel_title_foreground),
            android.app.NotificationManager.IMPORTANCE_LOW
        ).apply {
            description = context.getString(R.string.channel_description_foreground)
        }
        manager.createNotificationChannel(channel)
    }

    fun getNotificationForForegroundService(
        context: Context, title: String? = null, text: String? = null
    ): Notification {
        return Notification.Builder(
            context, FOREGROUND_SERVICE_CHANNEL_ID
        ).apply {
            setSmallIcon(R.drawable.icon_security)
            title?.let { setContentTitle(it) }
            text?.let { setContentText(it) }
            setShowWhen(false)
        }.build()
    }
}