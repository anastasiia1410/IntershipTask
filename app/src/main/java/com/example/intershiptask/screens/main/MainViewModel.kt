package com.example.intershiptask.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intershiptask.core.preferences.AppPreference
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val preference: AppPreference) : ViewModel() {
    val isFirstStart = MutableStateFlow(true)
    val isNotificationClick = MutableSharedFlow<Boolean>()
    val idFlow = MutableStateFlow(-1)

    fun setScreen() = viewModelScope.launch {
        idFlow.emit(preference.id!!)
    }
}