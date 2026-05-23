package com.panov.proxy.utils

import android.app.Application
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val settings = Settings(application.applicationContext)


    val language = getLoadedStateFlow(
        Settings.General.LANGUAGE, Settings.languages[0]
    )

    val theme = getLoadedStateFlow(
        Settings.General.THEME, Settings.themes[0]
    )

    val log = getStateFlow(
        Settings.General.LOG, Settings.logs[0]
    )

    val autoCheckIP = getStateFlow(
        Settings.General.AUTO_CHECK_IP, false
    )

    val displaySpeed = getStateFlow(
        Settings.General.DISPLAY_SPEED, false
    )

    val region = getStateFlow(
        Settings.Routing.REGION, Settings.regions[0]
    )

    val outboundDefault = getStateFlow(
        Settings.Routing.OUTBOUND_DEFAULT, Settings.outbounds[0]
    )

    val blockAdvertisements = getStateFlow(
        Settings.Routing.BLOCK_ADVERTISEMENTS, false
    )

    val xrayPortUseCustom = getStateFlow(
        Settings.Privacy.XRAY_PORT_USE_CUSTOM, false
    )

    val xrayPortCustom = getStateFlow(
        Settings.Privacy.XRAY_PORT_CUSTOM, 0
    )

    val sendHWID = getStateFlow(
        Settings.Privacy.SEND_HWID, false
    )


    fun updateLanguage(value: String) {
        updateData(Settings.General.LANGUAGE, value)
    }

    fun updateTheme(value: String) {
        updateData(Settings.General.THEME, value)
    }

    fun updateLog(value: String) {
        updateData(Settings.General.LOG, value)
    }

    fun updateAutoCheckIP(value: Boolean) {
        updateData(Settings.General.AUTO_CHECK_IP, value)
    }

    fun updateDisplaySpeed(value: Boolean) {
        updateData(Settings.General.DISPLAY_SPEED, value)
    }

    fun updateRegion(value: String) {
        updateData(Settings.Routing.REGION, value)
    }

    fun updateOutboundDefault(value: String) {
        updateData(Settings.Routing.OUTBOUND_DEFAULT, value)
    }

    fun updateBlockAdvertisements(value: Boolean) {
        updateData(Settings.Routing.BLOCK_ADVERTISEMENTS, value)
    }

    fun updateXrayPortUseCustom(value: Boolean) {
        updateData(Settings.Privacy.XRAY_PORT_USE_CUSTOM, value)
    }

    fun updateXrayPortCustom(value: Int) {
        updateData(Settings.Privacy.XRAY_PORT_CUSTOM, value)
    }

    fun updateSendHWID(value: Boolean) {
        updateData(Settings.Privacy.SEND_HWID, value)
    }


    private fun <T> getLoadedStateFlow(key: Preferences.Key<T>, default: T): StateFlow<T> {
        return getStateFlow(
            key, settings.getLoadedData(key, default)
        )
    }

    private fun <T> getStateFlow(key: Preferences.Key<T>, default: T): StateFlow<T> {
        return settings.getData(key, default).stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(5000), default
        )
    }

    private fun <T> updateData(key: Preferences.Key<T>, value: T) {
        viewModelScope.launch {
            settings.setData(key, value)
        }
    }
}