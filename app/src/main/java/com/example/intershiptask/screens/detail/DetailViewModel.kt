package com.example.intershiptask.screens.detail

import com.example.intershiptask.core.BaseViewModel
import com.example.intershiptask.core.preferences.AppPreference
import com.example.intershiptask.screens.detail.use_case.GetItemByIdUseCase
import com.example.intershiptask.screens.detail.use_case.find_use_case.GetUseCaseByEventDetail

class DetailViewModel(appPreference: AppPreference) :
    BaseViewModel<DetailEvent, DetailState>(
        getUseCaseForEvent = GetUseCaseByEventDetail(),
        useCases = listOf(GetItemByIdUseCase()),
        reducer = DetailReducer(appPreference),
        initialState = DetailState.initial()
    ) {
    fun getItemById() {
        handleEvent(
            GetItemByIdUseCase().invoke(
                DetailEvent.GetItemById(state.value.id),
                state.value
            )
        )
    }
}