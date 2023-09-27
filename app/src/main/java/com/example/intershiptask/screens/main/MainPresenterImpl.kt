package com.example.intershiptask.screens.main

import com.example.intershiptask.core.preferences.AppPreference

class MainPresenterImpl(private val preference: AppPreference) : MainPresenter {
    override var isServiceRunning: Boolean = false
    override var idValue: Int = 0
    private lateinit var mainView: MainView

    override fun attachView(view: MainView) {
        mainView = view
    }
    override fun setIdValue() {
        idValue = preference.id
        mainView.navigate(idValue)
    }
}