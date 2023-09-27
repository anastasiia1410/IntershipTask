package com.example.intershiptask.screens.main

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intershiptask.core.Service
import com.example.intershiptask.core.preferences.AppPreference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val preference: AppPreference) : ViewModel() {
    private var _isServiceRunning = MutableStateFlow(false)

    private var _isNotificationClick = MutableStateFlow(false)
    val isNotificationClick: StateFlow<Boolean>
        get() = _isNotificationClick.asStateFlow()

    private var _idFlow = MutableStateFlow(-1)
    val idFlow: StateFlow<Int>
        get() = _idFlow.asStateFlow()

    fun startServiceIfNeeded(context: Context) {
        viewModelScope.launch {
            _isServiceRunning.collect { isServiceRunning ->
                if (!isServiceRunning) {
                    ContextCompat.startForegroundService(
                        context,
                        Service.createIntent(
                            context,
                            Service.ACTION_START_FOREGROUND_SERVICE
                        )
                    )
                    _isServiceRunning.emit(true)
                }
            }
        }
    }

    fun checkAndRequestPermissions(requestPermissionLauncher: ActivityResultLauncher<String>) {
        viewModelScope.launch {
            _isServiceRunning.collect { isServiceRunning ->
                if (!isServiceRunning && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }

    fun handleNotificationClick() {
        viewModelScope.launch {
            _isNotificationClick.emit(true)
            _idFlow.emit(preference.id)
        }
    }
}