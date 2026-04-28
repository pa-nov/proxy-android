package com.panov.proxy.core.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun ProxyTheme(style: String = "STATIC+SYSTEM", content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (Build.VERSION.SDK_INT >= 31 && style.startsWith("DYNAMIC")) {
            if (style.endsWith("DARK")) {
                getDynamicDarkTheme()
            } else if (style.endsWith("LIGHT")) {
                getDynamicLightTheme()
            } else if (isSystemInDarkTheme()) {
                getDynamicDarkTheme()
            } else {
                getDynamicLightTheme()
            }
        } else {
            if (style.endsWith("DARK")) {
                getDarkTheme()
            } else if (style.endsWith("LIGHT")) {
                getLightTheme()
            } else if (isSystemInDarkTheme()) {
                getDarkTheme()
            } else {
                getLightTheme()
            }
        }, typography = getTypography(), content = content
    )
}