package com.example.intershiptask.screens.items

import com.example.intershiptask.core.preferences.AppPreference
import com.example.intershiptask.screens.entity.Item

class ItemsPresenterImpl(private val preference: AppPreference) : ItemsPresenter {

    override val itemList = Item.getItems()
    override fun saveId(id: Int) {
        preference.saveId(id)
    }
}