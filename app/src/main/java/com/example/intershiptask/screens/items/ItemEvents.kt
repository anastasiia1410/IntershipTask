package com.example.intershiptask.screens.items

import com.example.intershiptask.screens.entity.Item

sealed class ItemEvents {
    data object None : ItemEvents()

    data object GetList : ItemEvents()
    data class ShowList(val items : List<Item>) : ItemEvents()
    data class SaveId(val id: Int) : ItemEvents()

    data class Error(val error : String) : ItemEvents()
}
