package com.example.intershiptask.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<Event, State>(
    private val getUseCaseForEvent: GetUseCaseByEvent<Event, State>,
    private val useCases: List<UseCase<Event, State>>,
    private val reducer: Reducer<Event, State>,
    initialState: State,
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State>
        get() = _state.asStateFlow()

    protected fun handleEvent(event: Event) {
        val newState = reducer.reduce(event, state.value)
        _state.value = newState
        val useCase = getUseCaseForEvent.getUseCaseForEvent(event)
        useCase.canHandle(event)
        val result = useCase.invoke(event, state.value)
        //handleEvent(result)
    }
}
