package com.panov.proxy.core.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ProxyTheme(theme: String = "STATIC+SYSTEM", content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (Build.VERSION.SDK_INT >= 31 && theme.startsWith("DYNAMIC")) {
            when {
                theme.endsWith("BLACK") -> getDynamicDarkTheme().copy(background = getColor("000000"))
                theme.endsWith("DARK") -> getDynamicDarkTheme()
                theme.endsWith("LIGHT") -> getDynamicLightTheme()
                isSystemInDarkTheme() -> getDynamicDarkTheme()
                else -> getDynamicLightTheme()
            }
        } else {
            when {
                theme.endsWith("BLACK") -> getDarkTheme().copy(background = getColor("000000"))
                theme.endsWith("DARK") -> getDarkTheme()
                theme.endsWith("LIGHT") -> getLightTheme()
                isSystemInDarkTheme() -> getDarkTheme()
                else -> getLightTheme()
            }
        }, typography = getTypography(), content = content
    )
}

fun getColor(hex: String): Color {
    require(hex.length == 6)
    return Color("FF${hex}".toLong(16))
}