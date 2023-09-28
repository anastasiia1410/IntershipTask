package com.example.intershiptask.screens.items

interface ItemEvents {
    data object ShowList : ItemEvents
    data class OpenDetailItemById(val id: Int) : ItemEvents
}
