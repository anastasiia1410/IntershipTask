package com.example.intershiptask.screens.main

import com.example.intershiptask.core.Reducer
import com.example.intershiptask.core.preferences.AppPreference

class MainReducer(private val appPreference: AppPreference) : Reducer<MainEvent, MainState> {
    override fun reduce(event: MainEvent, state: MainState): MainState {
          return  MainState(appPreference.id)
    }
}