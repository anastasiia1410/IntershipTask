package com.example.intershiptask.screens.items

import com.example.intershiptask.screens.entity.Item

interface ItemsView {
    fun handlerClick(item: Item)
}