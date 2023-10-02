package com.example.intershiptask.screens.detail

import com.example.intershiptask.screens.entity.Item


sealed class DetailEvent {

    data object GetItemById : DetailEvent()

    data class ItemReceived(val item: Item) : DetailEvent()

    data class Error(val error: String) : DetailEvent()

}