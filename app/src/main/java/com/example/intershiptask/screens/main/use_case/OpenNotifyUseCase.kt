package com.example.intershiptask.screens.main.use_case

import com.example.intershiptask.core.UseCase
import com.example.intershiptask.screens.main.MainEvent
import com.example.intershiptask.screens.main.MainState

class OpenNotifyUseCase : UseCase<MainEvent, MainState> {
    override fun canHandle(event: MainEvent): Boolean {
        return event is MainEvent.NavigateFromNotify
    }

    override fun invoke(event: MainEvent, state: MainState): MainEvent {
        return MainEvent.NavigateFromNotify(state.chooseId)
    }
}