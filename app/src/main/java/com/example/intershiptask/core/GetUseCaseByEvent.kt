package com.example.intershiptask.core

abstract class GetUseCaseByEvent<Event, State> {
    abstract fun getUseCaseForEvent(event: Event) : UseCase<Event, State>
}