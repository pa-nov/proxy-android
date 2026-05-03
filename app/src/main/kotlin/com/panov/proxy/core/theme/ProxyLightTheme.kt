package com.panov.proxy.core.theme

import androidx.annotation.RequiresApi
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun getLightTheme(): ColorScheme {
    return lightColorScheme(
        background = Color("FFFFFF"),
        surface = Color("EEEEEE"),
        surfaceContainer = Color("DDDDDD"),
        inversePrimary = Color("DDDDDD"),
        surfaceVariant = Color("CCCCCC"),
        primary = Color("202020"),
        onBackground = Color("000000"),
        onSurface = Color("000000"),
        onSurfaceVariant = Color("808080"),
        onPrimary = Color("FFFFFF")
    ).applyLightColors()
}

@Composable
@RequiresApi(31)
fun getDynamicLightTheme(): ColorScheme {
    return dynamicLightColorScheme(LocalContext.current).applyLightColors()
}

private fun ColorScheme.applyLightColors(): ColorScheme {
    val green = Color("DDEEDD")
    val yellow = Color("FFEECC")
    val red = Color("FFDDDD")

    val onGreen = Color("226622")
    val onYellow = Color("886600")
    val onRed = Color("BB2222")

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