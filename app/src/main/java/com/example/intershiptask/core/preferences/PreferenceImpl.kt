package com.example.intershiptask.core.preferences

import android.content.Context
import androidx.core.content.edit

class PreferenceImpl(context: Context) : AppPreference {
    private val sharedPreference =
        context.getSharedPreferences(APP_PREFERENCE_NAME, Context.MODE_PRIVATE)

    override val id: Int
        get() = sharedPreference.getInt(ID_KEY, -1)

    override fun saveId(id: Int?) {
        sharedPreference.edit{
            putInt(ID_KEY, id!!)
        }
    }

    companion object {
        private const val APP_PREFERENCE_NAME = "app.preferences"
        private const val ID_KEY = "id_key"
    }
}