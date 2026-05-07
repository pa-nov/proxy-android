package com.panov.proxy.core.theme

import androidx.annotation.RequiresApi
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

fun getLightTheme(): ColorScheme {
    return lightColorScheme(
        background = getColor("FFFFFF"),
        surface = getColor("EEEEEE"),
        surfaceContainer = getColor("DDDDDD"),
        inversePrimary = getColor("DDDDDD"),
        surfaceVariant = getColor("CCCCCC"),
        primary = getColor("202020"),
        onBackground = getColor("000000"),
        onSurface = getColor("000000"),
        onSurfaceVariant = getColor("808080"),
        onPrimary = getColor("FFFFFF")
    ).applyLightColors()
}

@Composable
@RequiresApi(31)
fun getDynamicLightTheme(): ColorScheme {
    return dynamicLightColorScheme(LocalContext.current).applyLightColors()
}

private fun ColorScheme.applyLightColors(): ColorScheme {
    val green = getColor("DDEEDD")
    val yellow = getColor("FFEECC")
    val red = getColor("FFDDDD")

    val onGreen = getColor("226622")
    val onYellow = getColor("886600")
    val onRed = getColor("BB2222")

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