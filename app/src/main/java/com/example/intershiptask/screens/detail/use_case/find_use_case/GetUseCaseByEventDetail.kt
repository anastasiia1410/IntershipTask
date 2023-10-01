package com.example.intershiptask.screens.detail.use_case.find_use_case

import com.example.intershiptask.core.GetUseCaseByEvent
import com.example.intershiptask.core.UseCase
import com.example.intershiptask.screens.detail.DetailEvent
import com.example.intershiptask.screens.detail.DetailState
import com.example.intershiptask.screens.detail.use_case.GetItemByIdUseCase

class GetUseCaseByEventDetail : GetUseCaseByEvent<DetailEvent, DetailState>() {
    override fun getUseCaseForEvent(event: DetailEvent): UseCase<DetailEvent, DetailState> {
            return GetItemByIdUseCase()
    }
}