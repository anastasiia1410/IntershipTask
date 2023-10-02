package com.example.intershiptask.screens.detail.use_case

import com.example.intershiptask.core.UseCase
import com.example.intershiptask.core.preferences.AppPreference
import com.example.intershiptask.screens.detail.DetailEvent
import com.example.intershiptask.screens.detail.DetailState
import com.example.intershiptask.screens.entity.Item

class GetItemByIdUseCase(private val appPreference: AppPreference) :
    UseCase<DetailEvent, DetailState> {
    override fun canHandle(event: DetailEvent): Boolean {
        return event is DetailEvent.GetItemById
    }

    override fun invoke(event: DetailEvent, state: DetailState): DetailEvent {
        return (event as? DetailEvent.GetItemById)?.let {
            val saveId = appPreference.id
            val item = Item.getItems().find { it.id == saveId }
                ?: return DetailEvent.Error("item not find")
            return DetailEvent.ItemReceived(item)
        } ?: DetailEvent.Error("wrong ")
    }
}