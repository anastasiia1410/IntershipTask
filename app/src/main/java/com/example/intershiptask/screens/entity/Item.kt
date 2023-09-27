package com.example.intershiptask.screens.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(val id: Int, val name: String, val description: String) : Parcelable {
    companion object {
        fun getItems(): List<Item> {
            return List(size = 20) {
                Item(it, "Name $it", "Description $it")
            }
        }
    }
}
