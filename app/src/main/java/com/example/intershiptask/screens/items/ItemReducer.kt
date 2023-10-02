package com.example.intershiptask.screens.items

import com.example.intershiptask.core.Reducer

class ItemReducer : Reducer<ItemEvents, ItemStates> {
    override fun reduce(event: ItemEvents, state: ItemStates): ItemStates {
        return when (event) {

            is ItemEvents.GetList -> state

            is ItemEvents.ShowList ->
                state.copy(items = event.items)

            is ItemEvents.SaveId ->
                ItemStates(items = state.items, chooseId = event.id)

            is ItemEvents.None -> state

            is ItemEvents.Error -> state
        }
    }
}