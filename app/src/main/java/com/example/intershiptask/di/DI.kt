package com.example.intershiptask.di

import com.example.intershiptask.core.App
import com.example.intershiptask.core.preferences.AppPreference
import com.example.intershiptask.core.preferences.PreferenceImpl
import com.example.intershiptask.screens.items.ItemsViewModel
import com.example.intershiptask.screens.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

private val appModule = module {
    single<AppPreference> { PreferenceImpl(get()) }

    viewModel { MainViewModel(get()) }
    viewModel { ItemsViewModel(get()) }
}

fun App.initKoin() {
    startKoin {
        androidContext(this@initKoin)
        modules(appModule)
    }
}