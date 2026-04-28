package com.panov.proxy

import android.app.Application
import com.panov.proxy.utils.Settings

class ProxyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Settings.load(applicationContext)
    }
}