package com.panov.proxy.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object Settings {
    private const val FILE_NAME = "SETTINGS"


    private lateinit var sharedPreferences: SharedPreferences

    fun load(context: Context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    fun isExists(key: String): Boolean {
        return sharedPreferences.contains(key)
    }


    fun getBoolean(key: String, defValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit { putBoolean(key, value) }
    }


    fun getInt(key: String, defValue: Int = 0): Int {
        return sharedPreferences.getInt(key, defValue)
    }

    fun setInt(key: String, value: Int) {
        sharedPreferences.edit { putInt(key, value) }
    }


    fun getLong(key: String, defValue: Long = 0): Long {
        return sharedPreferences.getLong(key, defValue)
    }

    fun setLong(key: String, value: Long) {
        sharedPreferences.edit { putLong(key, value) }
    }


    fun getString(key: String, defValue: String = ""): String {
        return sharedPreferences.getString(key, defValue) ?: defValue
    }

    fun setString(key: String, value: String) {
        sharedPreferences.edit { putString(key, value) }
    }
}