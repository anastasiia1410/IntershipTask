package com.example.intershiptask.screens.items.use_cases

import com.example.intershiptask.core.UseCase
import com.example.intershiptask.screens.entity.Item
import com.example.intershiptask.screens.items.ItemEvents
import com.example.intershiptask.screens.items.ItemStates

class ShowListUseCase : UseCase<ItemEvents, ItemStates> {
    override fun canHandle(event: ItemEvents): Boolean {
        return event is ItemEvents.GetList
    }

    override fun invoke(event: ItemEvents, state: ItemStates): ItemEvents {
        return (event as? ItemEvents.GetList)?.let {
            val items = Item.getItems()
            return ItemEvents.ShowList(items)
        } ?: ItemEvents.Error("Wrong event type : $event")
    }
}