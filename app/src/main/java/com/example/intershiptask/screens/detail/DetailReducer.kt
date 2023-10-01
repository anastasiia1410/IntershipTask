package com.example.intershiptask.screens.detail

import com.example.intershiptask.core.Reducer
import com.example.intershiptask.core.preferences.AppPreference
import com.example.intershiptask.screens.entity.Item

class DetailReducer(private val appPreference: AppPreference) : Reducer<DetailEvent, DetailState> {
    override fun reduce(event: DetailEvent, state: DetailState): DetailState {
        val item = Item.getItems().find { it.id == appPreference.id }
        return DetailState(appPreference.id, item)
    }
}