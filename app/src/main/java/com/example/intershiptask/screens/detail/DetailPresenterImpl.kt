package com.example.intershiptask.screens.detail

import com.example.intershiptask.screens.entity.Item

class DetailPresenterImpl : DetailPresenter{
   override  fun getItemById(id: Int): Item? {
        return Item.getItems().find { it.id == id }
    }
}