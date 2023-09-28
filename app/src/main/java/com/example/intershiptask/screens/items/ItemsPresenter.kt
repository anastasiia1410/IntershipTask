package com.example.intershiptask.screens.items

import com.example.intershiptask.screens.entity.Item

interface ItemsPresenter {
    val itemList : List<Item>
    fun attachView(view: ItemsView)

    fun loadItemsList()

    fun onItemClick(item: Item)
}