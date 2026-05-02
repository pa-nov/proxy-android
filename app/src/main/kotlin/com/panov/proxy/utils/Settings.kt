package com.panov.proxy.utils

import android.content.Context
import android.os.Build
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.settings: DataStore<Preferences> by preferencesDataStore("settings")

class Settings(private val context: Context) {
    companion object {
        val languages = buildList {
            add("")
            add("en")
            add("ru")
        }.toTypedArray()

        val themes = buildList {
            add("STATIC+SYSTEM")
            add("STATIC+LIGHT")
            add("STATIC+DARK")
            if (Build.VERSION.SDK_INT >= 31) {
                add("DYNAMIC+SYSTEM")
                add("DYNAMIC+LIGHT")
                add("DYNAMIC+DARK")
            }
        }.toTypedArray()

        val logs = buildList {
            add("none")
            add("error")
            add("warning")
            add("info")
            add("debug")
        }.toTypedArray()

        val regions = buildList {
            add("global")
            add("russia")
        }.toTypedArray()
    }

    object General {
        val LANGUAGE = stringPreferencesKey("language")
        val THEME = stringPreferencesKey("theme")
        val LOG = stringPreferencesKey("log")
    }

    object Routing {
        val REGION = stringPreferencesKey("region")
    }

    object Privacy {
        val EXAMPLE = stringPreferencesKey("example")
    }

    object Libraries {
        val EXAMPLE = stringPreferencesKey("example")
    }

    fun <T> getData(key: Preferences.Key<T>, default: T): Flow<T> {
        return context.settings.data.map { preferences ->
            preferences[key] ?: default
        }
    }

    suspend fun <T> setData(key: Preferences.Key<T>, value: T) {
        context.settings.edit { preferences ->
            preferences[key] = value
        }
    }
}