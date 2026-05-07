package com.panov.proxy.core.theme

import androidx.annotation.RequiresApi
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

fun getDarkTheme(): ColorScheme {
    return darkColorScheme(
        background = getColor("101010"),
        surface = getColor("181818"),
        surfaceContainer = getColor("202020"),
        inversePrimary = getColor("202020"),
        surfaceVariant = getColor("282828"),
        primary = getColor("DDDDDD"),
        onBackground = getColor("FFFFFF"),
        onSurface = getColor("FFFFFF"),
        onSurfaceVariant = getColor("808080"),
        onPrimary = getColor("000000")
    ).applyDarkColors()
}

@Composable
@RequiresApi(31)
fun getDynamicDarkTheme(): ColorScheme {
    return dynamicDarkColorScheme(LocalContext.current).applyDarkColors()
}

private fun ColorScheme.applyDarkColors(): ColorScheme {
    val green = getColor("224422")
    val yellow = getColor("443311")
    val red = getColor("442222")

    val onGreen = getColor("AAEEAA")
    val onYellow = getColor("EEDD88")
    val onRed = getColor("EE9999")

    return this.copy(
        primaryFixed = green,
        primaryFixedDim = green,
        secondaryFixed = yellow,
        secondaryFixedDim = yellow,
        tertiaryFixed = red,
        tertiaryFixedDim = red,
        onPrimaryFixed = onGreen,
        onPrimaryFixedVariant = onGreen,
        onSecondaryFixed = onYellow,
        onSecondaryFixedVariant = onYellow,
        onTertiaryFixed = onRed,
        onTertiaryFixedVariant = onRed
    )
}