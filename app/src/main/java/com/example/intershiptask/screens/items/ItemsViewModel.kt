package com.example.intershiptask.screens.items

import com.example.intershiptask.core.BaseViewModel
import com.example.intershiptask.core.preferences.AppPreference
import com.example.intershiptask.screens.items.use_cases.SaveItemIdUseCase
import com.example.intershiptask.screens.items.use_cases.ShowListUseCase

class ItemsViewModel(
    appPreference: AppPreference,
) : BaseViewModel<ItemEvents, ItemStates>(
    useCases = listOf(ShowListUseCase(), SaveItemIdUseCase(appPreference = appPreference)),
    reducer = ItemReducer(),
    initialState = ItemStates.initial()
) {

    fun showList() {
        handleEvent(ItemEvents.GetList)
    }

    fun saveId(id : Int){
        handleEvent(ItemEvents.SaveId(id))
    }


}