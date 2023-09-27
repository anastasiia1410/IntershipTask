package com.example.intershiptask.di

import android.content.Context
import android.content.SharedPreferences
import com.example.intershiptask.core.App
import com.example.intershiptask.core.preferences.AppPreference
import com.example.intershiptask.core.preferences.PreferenceImpl
import com.example.intershiptask.screens.detail.DetailViewModel
import com.example.intershiptask.screens.items.ItemsViewModel
import com.example.intershiptask.screens.main.MainPresenter
import com.example.intershiptask.screens.main.MainPresenterImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

private const val APP_PREFERENCE_NAME = "app.preferences"

private val appModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            APP_PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
    }
    single<AppPreference> { PreferenceImpl(get()) }
    factory<MainPresenter> { MainPresenterImpl(get()) }
    viewModel { ItemsViewModel(get()) }
    viewModel { DetailViewModel() }
}

fun App.initKoin() {
    startKoin {
        androidContext(this@initKoin)
        modules(appModule)
    }
}