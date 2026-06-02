package com.panov.proxy

import android.content.Context
import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor
import android.util.Log
import com.panov.proxy.utils.LocaleManager.applyLocaleFromSettings
import com.panov.proxy.utils.NotificationManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class XrayService : VpnService() {
    companion object {
        const val ACTION_TOGGLE = "XRAY_SERVICE_TOGGLE"
        const val ACTION_START = "XRAY_SERVICE_START"
        const val ACTION_STOP = "XRAY_SERVICE_STOP"
    }

    private var tun: ParcelFileDescriptor? = null
    private var example: Job? = null


    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context.applyLocaleFromSettings())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return when (intent?.action) {
            ACTION_TOGGLE -> {
                if (Application.isProxyEnabled.value) {
                    onStartCommand(intent.setAction(ACTION_STOP), flags, startId)
                } else {
                    onStartCommand(intent.setAction(ACTION_START), flags, startId)
                }
            }

            ACTION_STOP -> {
                stopXray()
                START_NOT_STICKY
            }

            else -> {
                try {
                    startXray()
                    START_STICKY
                } catch (exception: Exception) {
                    Log.e("XrayService", "Error while starting xray-core", exception)
                    onStartCommand((intent ?: Intent()).setAction(ACTION_STOP), flags, startId)
                }
            }
        }
    }

    override fun onRevoke() {
        stopXray()
        super.onRevoke()
    }

    override fun onDestroy() {
        stopXray()
        super.onDestroy()
    }


    private fun startXray() {
        startForeground()
        Application.setProxyEnabled(true)
        Application.setProxyConnected(false)

        tun?.close()
        tun = Builder().apply {
            setMtu(1500)
            addAddress("10.10.10.10", 32)
            addAddress("fd00::1", 128)
            addRoute("0.0.0.0", 0)
            addRoute("::", 0)
        }.establish()

        if (tun == null) throw NullPointerException()

        example?.cancel()
        example = CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            Application.setProxyEnabled(true)
            Application.setProxyConnected(true)
            startForeground()
        }
    }

    private fun stopXray() {
        example?.cancel()
        example = null
        tun?.close()
        tun = null
        Application.setProxyEnabled(false)
        Application.setProxyConnected(false)
        stopForeground(STOP_FOREGROUND_REMOVE)
    }

    private fun startForeground() {
        startForeground(
            1, NotificationManager.getNotificationForForegroundService(
                baseContext, getString(
                    if (Application.isProxyConnected.value) {
                        R.string.state_connected
                    } else {
                        R.string.state_connecting
                    }
                ), getString(R.string.app_placeholder)
            )
        )
    }
}