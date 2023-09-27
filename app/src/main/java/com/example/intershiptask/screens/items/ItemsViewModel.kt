package com.example.intershiptask.screens.items

import androidx.lifecycle.ViewModel
import com.example.intershiptask.core.preferences.AppPreference
import com.example.intershiptask.screens.entity.Item

class ItemsViewModel(private val preference: AppPreference) : ViewModel() {

    val itemList = Item.getItems()
    fun saveId(id: Int) {
        preference.saveId(id)
    }
}