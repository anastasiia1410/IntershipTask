package com.example.intershiptask.screens.detail

import com.example.intershiptask.core.BaseViewModel
import com.example.intershiptask.core.preferences.AppPreference
import com.example.intershiptask.screens.detail.use_case.GetItemByIdUseCase

class DetailViewModel(appPreference: AppPreference) :
    BaseViewModel<DetailEvent, DetailState>(
        useCases = listOf(GetItemByIdUseCase(appPreference = appPreference)),
        reducer = DetailReducer(),
        initialState = DetailState.initial()
    ) {
    fun getItemById() {
        handleEvent(DetailEvent.GetItemById)
    }
}