package com.example.intershiptask.screens.detail

import androidx.lifecycle.ViewModel
import com.example.intershiptask.screens.entity.Item

class DetailViewModel : ViewModel() {
     fun getItemById(id: Int): Item? {
        return Item.getItems().find { it.id == id }
    }
}