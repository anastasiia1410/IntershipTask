package com.example.intershiptask.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intershiptask.screens.entity.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val _detailStatesFlow = MutableStateFlow(DetailState(-1, null))
    val detailStatesFlow: StateFlow<DetailState>
        get() = _detailStatesFlow.asStateFlow()

    private fun getItemById(id: Int): Item? {
        val item = Item.getItems().find { it.id == id }
        viewModelScope.launch {
            _detailStatesFlow.emit(DetailState(id = id, item = item))
        }
        return item
    }

    fun handleEvent(event: DetailEvent): Item? {
        return if (event is DetailEvent.GetItemById) {
            getItemById(id = event.id)
        } else {
            throw IllegalArgumentException()
        }
    }
}