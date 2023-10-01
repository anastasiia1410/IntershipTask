package com.example.intershiptask.screens.main

import com.example.intershiptask.core.BaseViewModel
import com.example.intershiptask.core.preferences.AppPreference
import com.example.intershiptask.screens.main.use_case.OpenNotifyUseCase
import com.example.intershiptask.screens.main.use_case.find_use_case.GetUseCaseByMainEvent

class MainViewModel(preference: AppPreference) : BaseViewModel<MainEvent, MainState>(
    getUseCaseForEvent = GetUseCaseByMainEvent(),
    useCases = listOf(OpenNotifyUseCase()),
    reducer = MainReducer(preference),
    initialState = MainState.initial()
) {
    var isServiceRunning: Boolean = false


    fun setIdValue() {
        handleEvent(MainEvent.NavigateFromNotify(state.value.chooseId))
    }
}
