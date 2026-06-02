package com.panov.proxy.utils

import android.content.Context
import android.os.Build
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

val Context.settings: DataStore<Preferences> by preferencesDataStore("settings")

class Settings(private val context: Context) {
    companion object {
        const val OUTBOUND_PROXY = "proxy"
        const val OUTBOUND_DIRECT = "direct"
        const val OUTBOUND_BLOCK = "block"

        const val PACKAGE_NONE = "none"
        const val PACKAGE_ALLOW = "allow"
        const val PACKAGE_DISALLOW = "disallow"

        val languages = buildList {
            add("")
            add("en")
            add("ru")
        }.toTypedArray()

        val themes = buildList {
            add("STATIC+SYSTEM")
            add("STATIC+LIGHT")
            add("STATIC+DARK")
            add("STATIC+BLACK")
            if (Build.VERSION.SDK_INT >= 31) {
                add("DYNAMIC+SYSTEM")
                add("DYNAMIC+LIGHT")
                add("DYNAMIC+DARK")
                add("DYNAMIC+BLACK")
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

        val outbounds = buildList {
            add(OUTBOUND_PROXY)
            add(OUTBOUND_DIRECT)
            add(OUTBOUND_BLOCK)
        }.toTypedArray()

        val packages = buildList {
            add(PACKAGE_NONE)
            add(PACKAGE_ALLOW)
            add(PACKAGE_DISALLOW)
        }.toTypedArray()
    }

    object General {
        val LANGUAGE = stringPreferencesKey("language")
        val THEME = stringPreferencesKey("theme")
        val LOG = stringPreferencesKey("log")
        val AUTO_CHECK_IP = booleanPreferencesKey("auto_check_ip")
        val DISPLAY_SPEED = booleanPreferencesKey("display_speed")
    }

    object Routing {
        val REGION = stringPreferencesKey("region")
        val OUTBOUND_DEFAULT = stringPreferencesKey("outbound_default")
        val BLOCK_ADVERTISEMENTS = booleanPreferencesKey("block_advertisements")
    }

    object Privacy {
        val XRAY_PORT_USE_CUSTOM = booleanPreferencesKey("xray_port_use_custom")
        val XRAY_PORT_CUSTOM = intPreferencesKey("xray_port_custom")
        val SEND_HWID = booleanPreferencesKey("send_hwid")
    }

    object Libraries {
        val XRAY_VERSION_CURRENT = stringPreferencesKey("xray_version_current")
        val XRAY_VERSION_TARGET = stringPreferencesKey("xray_version_target")
        val GEOIP_VERSION_CURRENT = stringPreferencesKey("geoip_version_current")
        val GEOIP_VERSION_TARGET = stringPreferencesKey("geoip_version_target")
        val GEOSITE_VERSION_CURRENT = stringPreferencesKey("geosite_version_current")
        val GEOSITE_VERSION_TARGET = stringPreferencesKey("geosite_version_target")
    }

    fun <T> getLoadedData(key: Preferences.Key<T>, default: T): T {
        return runBlocking {
            getData(key, default).first()
        }
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