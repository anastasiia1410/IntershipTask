package com.example.intershiptask.screens.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intershiptask.core.preferences.AppPreference
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class NavigationViewModel(private val preference: AppPreference) : ViewModel() {
    val idFlow = MutableSharedFlow<Int>()

    fun setScreen() = viewModelScope.launch {
        idFlow.emit(preference.id!!)
    }
}
