package com.example.intershiptask.screens.detail.use_case

import com.example.intershiptask.core.UseCase
import com.example.intershiptask.screens.detail.DetailEvent
import com.example.intershiptask.screens.detail.DetailState

class GetItemByIdUseCase : UseCase<DetailEvent, DetailState> {
    override fun canHandle(event: DetailEvent): Boolean {
        return event is DetailEvent.GetItemById
    }

    override fun invoke(event: DetailEvent, state: DetailState): DetailEvent {
       return DetailEvent.GetItemById(state.id)
    }
}