package com.example.intershiptask.screens.items

import com.example.intershiptask.core.UseCase
import com.example.intershiptask.screens.entity.Item

class ShowListUseCase : UseCase<ItemEvents, ItemStates> {
    override fun canHandle(event: ItemEvents): Boolean {
        return event is ItemEvents.ShowList
    }

    override fun invoke(event: ItemEvents, state: ItemStates): ItemEvents {
        var items: List<Item> = emptyList()
        if (event is ItemEvents.ShowList) {
            items = Item.getItems()
        }
        return ItemEvents.ShowList
    }
}