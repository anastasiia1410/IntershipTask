package com.example.intershiptask.screens.detail


interface DetailEvent {

    class GetItemById(val id : Int) : DetailEvent

}