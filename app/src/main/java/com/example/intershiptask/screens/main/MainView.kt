package com.example.intershiptask.screens.main

interface MainView {

    fun requestPermissionAndStartService()
    fun navigate(id: Int)
}