package com.example.intershiptask.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intershiptask.core.preferences.AppPreference
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel(private val preference: AppPreference) : ViewModel() {

    private val _mainStatesFlow =
        MutableStateFlow(MainState(chooseId = 0))

    private val _mainNavigationFlow =
        MutableSharedFlow<MainNavigationEvent>()
    val mainNavigationFlow: SharedFlow<MainNavigationEvent>
        get() = _mainNavigationFlow.asSharedFlow()


    var isServiceRunning: Boolean = false


    private fun setIdValue() {
        viewModelScope.launch {
            _mainStatesFlow.emit(
                MainState(
                    chooseId = preference.id
                )
            )
        }
    }

    fun handleEvent(mainEvent: MainEvent) {
        if (mainEvent is MainEvent.OpenDetailItemOrListFromNotify) {
            setIdValue()
            viewModelScope.launch {
                if (_mainStatesFlow.value.chooseId != -1) {
                    _mainNavigationFlow.emit(
                        MainNavigationEvent.NavigateToDetailFragment(
                            _mainStatesFlow.value.chooseId
                        )
                    )
                } else {
                    _mainNavigationFlow.emit(MainNavigationEvent.NavigateToItemListFragment)
                }
            }
        }
    }
}
