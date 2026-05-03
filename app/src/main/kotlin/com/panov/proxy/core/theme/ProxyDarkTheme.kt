package com.panov.proxy.core.theme

import androidx.annotation.RequiresApi
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun getDarkTheme(): ColorScheme {
    return darkColorScheme(
        background = Color("000000"),
        surface = Color("181818"),
        surfaceContainer = Color("202020"),
        inversePrimary = Color("202020"),
        surfaceVariant = Color("282828"),
        primary = Color("DDDDDD"),
        onBackground = Color("FFFFFF"),
        onSurface = Color("FFFFFF"),
        onSurfaceVariant = Color("808080"),
        onPrimary = Color("000000")
    ).applyDarkColors()
}

@Composable
@RequiresApi(31)
fun getDynamicDarkTheme(): ColorScheme {
    return dynamicDarkColorScheme(LocalContext.current).applyDarkColors()
}

private fun ColorScheme.applyDarkColors(): ColorScheme {
    val green = Color("224422")
    val yellow = Color("443311")
    val red = Color("442222")

    val onGreen = Color("AAEEAA")
    val onYellow = Color("EEDD88")
    val onRed = Color("EE9999")

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