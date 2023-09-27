package com.example.intershiptask.screens.main

import androidx.lifecycle.ViewModel
import com.example.intershiptask.core.preferences.AppPreference

class MainViewModel(private val preference: AppPreference) : ViewModel() {
    var isServiceRunning: Boolean = false
    var idValue: Int = 0
    fun setIdValue() {
        idValue = preference.id
    }
}