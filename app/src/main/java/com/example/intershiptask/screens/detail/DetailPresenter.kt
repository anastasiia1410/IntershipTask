package com.example.intershiptask.screens.detail


interface DetailPresenter {
    fun attachView(view: DetailView)
    fun getItemById(id: Int)
}