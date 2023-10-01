package com.example.intershiptask.screens.main

data class MainState(val chooseId: Int){
    companion object{
        fun initial() : MainState{
           return MainState(chooseId = -1)
        }
    }
}