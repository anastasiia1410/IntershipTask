package com.example.intershiptask.screens.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intershiptask.core.preferences.AppPreference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ItemsViewModel(private val preference: AppPreference) : ViewModel() {
    val saveFlow = MutableStateFlow(-1)

    fun saveId(id : Int) = viewModelScope.launch{
        preference.saveId(id)
        saveFlow.emit(id)
    }
}