package com.example.intershiptask.screens.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(val id: Int, val name: String, val description: String) : Parcelable {
    companion object {
        fun getItems(): List<Item> {
            val items = mutableListOf<Item>()
            for (i in 0 until 20) {
                val item = Item(i, "Name $i", "Description $i")
                items.add(item)
            }
            return items
        }
    }
}
