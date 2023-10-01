package com.example.intershiptask.screens.items

import com.example.intershiptask.core.BaseViewModel
import com.example.intershiptask.core.preferences.AppPreference
import com.example.intershiptask.screens.items.use_cases.GetDetailByIdUseCase
import com.example.intershiptask.screens.items.use_cases.ShowListUseCase
import com.example.intershiptask.screens.items.use_cases.find_use_case.GetUseCaseByItemEvent

class ItemsViewModel(
    private val appPreference: AppPreference,
) : BaseViewModel<ItemEvents, ItemStates>(
    getUseCaseForEvent = GetUseCaseByItemEvent(),
    useCases = listOf(ShowListUseCase(), GetDetailByIdUseCase()),
    reducer = ItemReducer(),
    initialState = ItemStates.initial()
) {


    fun showList() {
        val event = ShowListUseCase().invoke(ItemEvents.ShowList(state.value.items), state.value)
        handleEvent(event)
    }

    fun getItemById(id : Int) {
        appPreference.saveId(id)
        handleEvent(
            GetDetailByIdUseCase().invoke(
                ItemEvents.OpenDetailItemById(appPreference.id), state.value
            )
        )
    }
}