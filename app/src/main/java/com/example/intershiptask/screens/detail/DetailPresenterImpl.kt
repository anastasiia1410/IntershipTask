package com.example.intershiptask.screens.detail

import com.example.intershiptask.screens.entity.Item

class DetailPresenterImpl : DetailPresenter {

    private lateinit var view: DetailView
    override fun attachView(view: DetailView) {
        this.view = view
    }

    override fun getItemById(id: Int) {
        val item = Item.getItems().find { it.id == id }
        view.setViews(item)
    }
}