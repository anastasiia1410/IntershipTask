package com.example.intershiptask.core.preferences

import android.content.SharedPreferences
import androidx.core.content.edit

class PreferenceImpl(private val sharedPreferences: SharedPreferences) : AppPreference {
    override val id: Int
        get() = sharedPreferences.getInt(ID_KEY, -1)

    override fun saveId(id: Int) {
        sharedPreferences.edit {
            putInt(ID_KEY, id)
        }
    }

    companion object {
        private const val ID_KEY = "id_key"
    }
}