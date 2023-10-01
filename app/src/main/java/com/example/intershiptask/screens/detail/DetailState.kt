package com.example.intershiptask.screens.detail

import com.example.intershiptask.screens.entity.Item
data class DetailState(val id: Int, val item: Item?){
    companion object{
        fun initial() : DetailState {
            return DetailState(id = -1, item = null)
        }
    }
}