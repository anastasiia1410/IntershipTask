package com.example.intershiptask.screens.items

import com.example.intershiptask.core.BaseViewModel
import com.example.intershiptask.core.preferences.AppPreference
import com.example.intershiptask.screens.entity.Item

class ItemsViewModel(
    private val appPreference: AppPreference,
) : BaseViewModel<ItemEvents, ItemStates>(
    useCases = listOf(ShowListUseCase()),
    reducer = ItemReducer(),
    initialState = ItemStates.initial()
) {


    fun showList() {
        handleEvent(ShowListUseCase().invoke(ItemEvents.ShowList, ItemStates(Item.getItems(), -1)))
    }
}