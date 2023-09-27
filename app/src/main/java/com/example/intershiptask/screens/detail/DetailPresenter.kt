package com.example.intershiptask.screens.detail

import com.example.intershiptask.screens.entity.Item

interface DetailPresenter {
    fun getItemById(id: Int): Item?
}