package com.example.intershiptask.screens.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intershiptask.core.preferences.AppPreference
import com.example.intershiptask.screens.entity.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ItemsViewModel(private val preference: AppPreference) : ViewModel() {
    private val _itemStatesFlow = MutableStateFlow(ItemStates(emptyList(), null))
    val itemsStatesFlow: StateFlow<ItemStates>
        get() = _itemStatesFlow.asStateFlow()


    private fun showList() {
        viewModelScope.launch {
            _itemStatesFlow.emit(
                (ItemStates(
                    items = Item.getItems(),
                    chooseId = _itemStatesFlow.value.chooseId
                ))
            )
        }
    }

    private fun saveId(id: Int) {
        preference.saveId(id)
        viewModelScope.launch {
            _itemStatesFlow.emit(
                ItemStates(
                    items = _itemStatesFlow.value.items,
                    chooseId = id
                )
            )
        }
    }

    fun handleEvent(event: ItemEvents) {
        when (event) {
            is ItemEvents.ShowList -> {
                showList()
            }

            is ItemEvents.OpenDetailItemById -> {
                saveId(id = event.id)

            }

            else -> {
                throw IllegalArgumentException()
            }
        }
    }
}