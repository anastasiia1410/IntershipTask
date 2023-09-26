package com.example.intershiptask.core

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LifecycleService
import com.example.intershiptask.core.preferences.AppPreference
import com.example.intershiptask.utils.createNotification
import com.example.intershiptask.utils.createNotificationChannel
import com.example.intershiptask.utils.notificationManager
import org.koin.android.ext.android.inject

class Service : LifecycleService() {
    private val preference by inject<AppPreference>()
    private var isServiceStart = false

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START_FOREGROUND_SERVICE -> {
                if (!isServiceStart) {
                    startForegroundService()
                    isServiceStart = true
                }
            }

            ACTION_STOP_FOREGROUND_SERVICE -> {
                stopService()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSelf()
    }

    private fun startForegroundService() {
        val notification = if (preference.id == -1) {
            createNotification(this@Service, -1, "Don't choose any item yet")
        } else {
            createNotification(this@Service, preference.id!!, "Chose item with id ${preference.id}")
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        this.notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun stopService() {
        stopForeground(STOP_FOREGROUND_REMOVE)
    }


    companion object {
        const val ACTION_START_FOREGROUND_SERVICE = "action_start_foreground_service"
        const val ACTION_STOP_FOREGROUND_SERVICE = "action_stop_foreground_service"
        const val NOTIFICATION_ID = 1

        fun createIntent(context: Context, action: String): Intent {
            return Intent(context, Service::class.java).setAction(action)
        }
    }
}

