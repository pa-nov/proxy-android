package com.panov.proxy

import android.content.Intent
import com.panov.proxy.utils.NotificationManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class Application : android.app.Application() {
    companion object {
        private val _isProxyEnabled = MutableStateFlow(false)
        val isProxyEnabled = _isProxyEnabled.asStateFlow()
        fun setProxyEnabled(value: Boolean) {
            _isProxyEnabled.value = value
        }

        private val _isProxyConnected = MutableStateFlow(false)
        val isProxyConnected = _isProxyConnected.asStateFlow()
        fun setProxyConnected(value: Boolean) {
            _isProxyConnected.value = value
        }
    }

    override fun onCreate() {
        super.onCreate()
        NotificationManager.createChannelForForegroundService(applicationContext)
    }

    fun toggleProxy() {
        runProxy(XrayService.ACTION_TOGGLE)
    }

    fun startProxy() {
        runProxy(XrayService.ACTION_START)
    }

    fun stopProxy() {
        runProxy(XrayService.ACTION_STOP)
    }

    private fun runProxy(action: String) {
        startService(
            Intent(applicationContext, XrayService::class.java).setAction(action)
        )
    }
}