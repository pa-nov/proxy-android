package com.panov.proxy.core.theme

import androidx.annotation.RequiresApi
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val accent = Color(0xE0E0E0)
private val onAccent = Color(0x000000)

private val green = Color(0x204020)
private val onGreen = Color(0x20C020)
private val yellow = Color(0x404020)
private val onYellow = Color(0xC0C020)
private val red = Color(0x402020)
private val onRed = Color(0xC02020)


@Composable
fun getDarkTheme(): ColorScheme {
    return darkColorScheme(
        background = Color(0x000000),
        onBackground = Color(0xFFFFFF),
        surface = Color(0x181818),
        onSurface = Color(0xFFFFFF),
        surfaceVariant = Color(0x282828),
        onSurfaceVariant = Color(0x808080),
        primary = accent,
        onPrimary = onAccent,
        secondary = accent,
        onSecondary = onAccent,
        tertiary = accent,
        onTertiary = onAccent
    ).applyDarkColors()
}

@Composable
@RequiresApi(31)
fun getDynamicDarkTheme(): ColorScheme {
    return dynamicDarkColorScheme(LocalContext.current).applyDarkColors()
}


private fun ColorScheme.applyDarkColors(): ColorScheme {
    return this.copy(
        primaryFixed = green,
        primaryFixedDim = green,
        onPrimaryFixed = onGreen,
        onPrimaryFixedVariant = onGreen,
        secondaryFixed = yellow,
        secondaryFixedDim = yellow,
        onSecondaryFixed = onYellow,
        onSecondaryFixedVariant = onYellow,
        tertiaryFixed = red,
        tertiaryFixedDim = red,
        onTertiaryFixed = onRed,
        onTertiaryFixedVariant = onRed
    )
}

private fun Color(color: Long): androidx.compose.ui.graphics.Color {
    return androidx.compose.ui.graphics.Color(0xFF000000 or color)
}