package com.example.intershiptask.utils

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.intershiptask.receiver.Receiver
import com.example.intershiptask.screens.items.ItemsListFragment.Companion.ITEM_KEY
import com.example.intershiptask.screens.items.ItemsListFragment.Companion.TAG

const val NOTIFICATION_CHANNEL_ID = "channel_default"
const val BROADCAST_ACTION = "com.example.intershiptask.MY_ACTION"
const val ACTIVITY_ACTION = "com.example.intershiptask.ACTIVITY_ACTION"



val Context.notificationManager: NotificationManager
    get() = getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager

fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationChannel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            "Notify",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        context.notificationManager.createNotificationChannel(notificationChannel)
    }
}

@SuppressLint("LaunchActivityFromNotification")
    fun createNotification(context: Context, id: Int, text: String): Notification {
        val intent = Intent(context, Receiver::class.java)
        intent.action = BROADCAST_ACTION
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

    return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID).apply {
        setContentTitle("Notification title")
        setContentIntent(pendingIntent)
        setContentText(text)
        setSmallIcon(androidx.appcompat.R.drawable.abc_btn_radio_to_on_mtrl_000)
        setOnlyAlertOnce(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            foregroundServiceBehavior = Notification.FOREGROUND_SERVICE_IMMEDIATE
        }
        Log.d(TAG, "Notification created, id = $id")

    }.build()
}




