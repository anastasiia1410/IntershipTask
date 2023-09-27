package com.example.intershiptask.screens.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intershiptask.core.preferences.AppPreference
import com.example.intershiptask.screens.entity.Item
import kotlinx.coroutines.launch

class ItemsViewModel(private val preference: AppPreference) : ViewModel() {
    val items = Item.getItems()
    fun saveId(id: Int) {
        viewModelScope.launch {
            preference.saveId(id)
        }
    }
}