package com.example.intershiptask.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.intershiptask.core.Service
import com.example.intershiptask.screens.main.MainActivity
import com.example.intershiptask.utils.ACTIVITY_ACTION
import com.example.intershiptask.utils.BROADCAST_ACTION


class Receiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            BROADCAST_ACTION -> {
                val navigationIntent = Intent(context, MainActivity::class.java)
                navigationIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                navigationIntent.action = ACTIVITY_ACTION
                context?.startService(
                    Service.createIntent(
                        context,
                        Service.ACTION_STOP_FOREGROUND_SERVICE
                    )
                )
                context?.startActivity(navigationIntent)
            }
        }
    }
}


