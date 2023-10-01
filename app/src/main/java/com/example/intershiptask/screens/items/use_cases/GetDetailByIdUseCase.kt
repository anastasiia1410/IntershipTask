package com.example.intershiptask.screens.items.use_cases

import com.example.intershiptask.core.UseCase
import com.example.intershiptask.screens.items.ItemEvents
import com.example.intershiptask.screens.items.ItemStates

class GetDetailByIdUseCase : UseCase<ItemEvents, ItemStates> {
    override fun canHandle(event: ItemEvents): Boolean {
        return event is ItemEvents.OpenDetailItemById
    }

    override fun invoke(event: ItemEvents, state: ItemStates): ItemEvents {
        return ItemEvents.OpenDetailItemById(state.chooseId)
    }
}