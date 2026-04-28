package com.panov.proxy.core.theme

import androidx.annotation.RequiresApi
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val accent = Color(0x202020)
private val onAccent = Color(0xFFFFFF)

private val green = Color(0xAAFFAA)
private val onGreen = Color(0x106010)
private val yellow = Color(0xFFFFAA)
private val onYellow = Color(0x606010)
private val red = Color(0xFFAAAA)
private val onRed = Color(0x601010)


@Composable
fun getLightTheme(): ColorScheme {
    return lightColorScheme(
        background = Color(0xFFFFFF),
        onBackground = Color(0x000000),
        surface = Color(0xF0F0F0),
        onSurface = Color(0x000000),
        surfaceVariant = Color(0xD0D0D0),
        onSurfaceVariant = Color(0x808080),
        primary = accent,
        onPrimary = onAccent,
        secondary = accent,
        onSecondary = onAccent,
        tertiary = accent,
        onTertiary = onAccent
    ).applyLightColors()
}

@Composable
@RequiresApi(31)
fun getDynamicLightTheme(): ColorScheme {
    return dynamicLightColorScheme(LocalContext.current).applyLightColors()
}


private fun ColorScheme.applyLightColors(): ColorScheme {
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