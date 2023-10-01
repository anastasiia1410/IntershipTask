package com.example.intershiptask.screens.main.use_case.find_use_case

import com.example.intershiptask.core.GetUseCaseByEvent
import com.example.intershiptask.core.UseCase
import com.example.intershiptask.screens.main.MainEvent
import com.example.intershiptask.screens.main.MainState
import com.example.intershiptask.screens.main.use_case.OpenNotifyUseCase

class GetUseCaseByMainEvent : GetUseCaseByEvent<MainEvent, MainState>() {
    override fun getUseCaseForEvent(event: MainEvent): UseCase<MainEvent, MainState> {
        return OpenNotifyUseCase()
    }
}