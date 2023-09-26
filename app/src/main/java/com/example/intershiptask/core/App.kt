package com.example.intershiptask.core

import android.app.Application
import com.example.intershiptask.di.initKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}