package com.example.intershiptask.screens.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.intershiptask.core.Service
import com.example.intershiptask.databinding.ActivityMainBinding
import com.example.intershiptask.utils.ACTIVITY_ACTION
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private var isServiceRunning = false

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                if (result) {
                    if (!isServiceRunning) {
                        ContextCompat.startForegroundService(
                            this,
                            Service.createIntent(this, Service.ACTION_START_FOREGROUND_SERVICE)
                        )
                        isServiceRunning = true
                    }
                }
            }

        if (!isServiceRunning) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent?.action == ACTIVITY_ACTION) {
            viewModel.setScreen()
            lifecycleScope.launch {
                viewModel.isFirstStart.emit(false)
            }
            lifecycleScope.launch {
                viewModel.isNotificationClick.emit(true)
            }

        }
    }
}

