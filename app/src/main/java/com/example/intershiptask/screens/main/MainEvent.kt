package com.example.intershiptask.screens.main

interface MainEvent {
    object OpenDetailItemOrListFromNotify : MainEvent
}

interface MainNavigationEvent {
    class NavigateToDetailFragment(val id: Int) : MainNavigationEvent

    object NavigateToItemListFragment : MainNavigationEvent
}