package com.example.intershiptask.screens.items

import com.example.intershiptask.screens.entity.Item

interface ItemsView {
    fun showItemsList(itemsList : List<Item>)
    fun handlerClick(item: Item)
}