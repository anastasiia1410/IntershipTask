package com.example.intershiptask.screens.items

import com.example.intershiptask.core.preferences.AppPreference
import com.example.intershiptask.screens.entity.Item

class ItemsPresenterImpl(private val preference: AppPreference) : ItemsPresenter {

    override val itemList = Item.getItems()

    private lateinit var view: ItemsView
    override fun attachView(view: ItemsView) {
        this.view = view
    }

    override fun onItemClick(item: Item) {
        preference.saveId(item.id)
        view.handlerClick(item)
    }
}