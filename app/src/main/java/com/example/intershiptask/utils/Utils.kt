package com.example.intershiptask.utils

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.intershiptask.R
import com.example.intershiptask.receiver.Receiver

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
fun createNotification(context: Context, id: Int): Notification {
    val intent = Intent(context, Receiver::class.java)
    intent.action = BROADCAST_ACTION
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_IMMUTABLE
    )

    return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID).apply {
        setContentTitle(context.getString(R.string.title))
        setContentIntent(pendingIntent)
        setContentText(getNotificationText(context, id))
        setSmallIcon(androidx.appcompat.R.drawable.abc_btn_radio_to_on_mtrl_000)
        setOnlyAlertOnce(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            foregroundServiceBehavior = Notification.FOREGROUND_SERVICE_IMMEDIATE
        }
    }.build()
}

fun getNotificationText(context: Context, id: Int) = if (id == -1) {
    context.getString(R.string.choose_nothing)
} else {
    context.getString(R.string.choose_item, id)
}




