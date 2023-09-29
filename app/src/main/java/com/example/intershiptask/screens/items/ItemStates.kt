package com.example.intershiptask.screens.items

import com.example.intershiptask.screens.entity.Item

data class ItemStates(
    val items: List<Item>,
    val chooseId: Int?,
){
    companion object{
        fun initial() : ItemStates{
           return ItemStates(items = emptyList(), chooseId = -1)
        }
    }
}




