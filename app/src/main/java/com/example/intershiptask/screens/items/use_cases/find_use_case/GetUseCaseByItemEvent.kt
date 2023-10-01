package com.example.intershiptask.screens.items.use_cases.find_use_case

import com.example.intershiptask.core.GetUseCaseByEvent
import com.example.intershiptask.core.UseCase
import com.example.intershiptask.screens.items.ItemEvents
import com.example.intershiptask.screens.items.ItemStates
import com.example.intershiptask.screens.items.use_cases.GetDetailByIdUseCase
import com.example.intershiptask.screens.items.use_cases.ShowListUseCase

class GetUseCaseByItemEvent : GetUseCaseByEvent<ItemEvents, ItemStates>() {
    override fun getUseCaseForEvent(event: ItemEvents): UseCase<ItemEvents, ItemStates> {
        return when (event) {
            ItemEvents.None -> {
                throw IllegalArgumentException("Error events")
            }

            is ItemEvents.OpenDetailItemById -> GetDetailByIdUseCase()
            is ItemEvents.ShowList -> ShowListUseCase()
        }
    }
}