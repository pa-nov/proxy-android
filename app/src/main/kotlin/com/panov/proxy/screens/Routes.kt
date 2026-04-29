package com.panov.proxy.screens

object Routes {
    const val HOME = "home"
    const val ABOUT = "about"
    const val DEVICE = "device"
    const val CONFIGS = "configs"
    const val SETTINGS = "settings"

    object Settings {
        const val GENERAL = "${SETTINGS}/general"
        const val ROUTING = "${SETTINGS}/routing"
        const val PRIVACY = "${SETTINGS}/privacy"
        const val LIBRARIES = "${SETTINGS}/libraries"
    }
}