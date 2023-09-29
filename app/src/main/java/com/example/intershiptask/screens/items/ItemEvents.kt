package com.example.intershiptask.screens.items

import com.example.intershiptask.screens.entity.Item

sealed class ItemEvents {
    data object None : ItemEvents()
    data object ShowList : ItemEvents()
    data class OpenDetailItemById(val id: Int) : ItemEvents()
}
