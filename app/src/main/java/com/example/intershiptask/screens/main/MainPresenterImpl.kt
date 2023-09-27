package com.example.intershiptask.screens.main

import com.example.intershiptask.core.preferences.AppPreference

class MainPresenterImpl(private val preference: AppPreference) : MainPresenter {
    override var isServiceRunning: Boolean = false
    override var idValue: Int = 0

    override fun setIdValue() {
        idValue = preference.id
    }
}