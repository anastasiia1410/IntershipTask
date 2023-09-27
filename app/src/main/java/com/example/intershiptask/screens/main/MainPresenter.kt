package com.example.intershiptask.screens.main

interface MainPresenter {
    var isServiceRunning: Boolean
    var idValue: Int
    fun setIdValue()
}