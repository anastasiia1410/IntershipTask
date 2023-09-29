package com.example.intershiptask.core

interface UseCase<Event, State> {
    fun canHandle(event: Event): Boolean
    fun invoke(event: Event, state: State): Event
}