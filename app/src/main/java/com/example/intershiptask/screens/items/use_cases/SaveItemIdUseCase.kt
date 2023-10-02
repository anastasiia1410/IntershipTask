package com.example.intershiptask.screens.items.use_cases

import com.example.intershiptask.core.UseCase
import com.example.intershiptask.core.preferences.AppPreference
import com.example.intershiptask.screens.items.ItemEvents
import com.example.intershiptask.screens.items.ItemStates

class SaveItemIdUseCase(private val appPreference: AppPreference) :
    UseCase<ItemEvents, ItemStates> {
    override fun canHandle(event: ItemEvents): Boolean {
        return event is ItemEvents.SaveId
    }

    override fun invoke(event: ItemEvents, state: ItemStates): ItemEvents {
        return (event as? ItemEvents.SaveId)?.let {
             appPreference.saveId(state.chooseId)
            return ItemEvents.None
        } ?: ItemEvents.Error("Wrong event type : $event")
    }
}