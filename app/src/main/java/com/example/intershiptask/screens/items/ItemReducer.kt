package com.example.intershiptask.screens.items

import com.example.intershiptask.core.Reducer
import com.example.intershiptask.screens.entity.Item

class ItemReducer : Reducer<ItemEvents, ItemStates> {
    override fun reduce(event: ItemEvents, state: ItemStates): ItemStates {
        return if (event is ItemEvents.ShowList) {
            ItemStates(Item.getItems(), state.chooseId)
        } else {
            ItemStates(state.items, state.chooseId)
        }
    }
}