package com.example.intershiptask.screens.detail

import com.example.intershiptask.core.Reducer

class DetailReducer : Reducer<DetailEvent, DetailState> {
    override fun reduce(event: DetailEvent, state: DetailState): DetailState {
        return when (event) {
            is DetailEvent.Error -> state
            DetailEvent.GetItemById -> state.copy()
            is DetailEvent.ItemReceived -> state.copy(id = event.item.id, item = event.item)
        }
    }
}