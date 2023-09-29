package com.example.intershiptask.screens.items

import com.example.intershiptask.core.Reducer

class ItemReducer : Reducer<ItemEvents, ItemStates> {
    override fun reduce(event: ItemEvents, state: ItemStates): ItemStates {
        return ItemStates(state.items, state.chooseId)
    }
}