package com.panov.proxy.utils

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.panov.proxy.R
import com.panov.proxy.utils.LocaleManager.applyLocale
import kotlinx.coroutines.delay
import java.util.Locale
import kotlin.time.DurationUnit
import kotlin.time.toDuration

object ComposeUtils {
    @Composable
    fun getPowerState(
        isProxyEnabled: Boolean, isProxyConnected: Boolean
    ): Triple<Color, Color, String> {
        return if (isProxyEnabled) {
            if (isProxyConnected) {
                Triple(
                    MaterialTheme.colorScheme.primaryFixed,
                    MaterialTheme.colorScheme.onPrimaryFixed,
                    stringResource(R.string.state_connected)
                )
            } else {
                var dots by remember { mutableStateOf("") }
                LaunchedEffect(Unit) {
                    while (true) {
                        delay(250.toDuration(DurationUnit.MILLISECONDS))
                        dots = when (dots) {
                            "" -> "."
                            "." -> ".."
                            ".." -> "..."
                            else -> ""
                        }
                    }
                }
                Triple(
                    MaterialTheme.colorScheme.secondaryFixed,
                    MaterialTheme.colorScheme.onSecondaryFixed,
                    stringResource(R.string.state_connecting) + dots
                )
            }
        } else {
            Triple(
                MaterialTheme.colorScheme.tertiaryFixed,
                MaterialTheme.colorScheme.onTertiaryFixed,
                stringResource(R.string.state_disconnected)
            )
        }
    }

    @Composable
    fun getLanguageText(language: String, locale: String = language): String {
        val system = LocalContext.current.applyLocale(
            @SuppressLint("NonObservableLocale") Locale.getDefault().language
        ).getString(R.string.code)
        val locale = locale.ifBlank { system }
        return if (language.isBlank()) {
            buildString {
                append(
                    LocalContext.current.applyLocale(locale).getString(
                        R.string.settings_language_system
                    )
                )
                append(" (${LocaleManager.getDisplayLanguage(system, locale)})")
            }
        } else {
            LocaleManager.getDisplayLanguage(language, locale)
        }
    }

    @Composable
    fun getThemeText(theme: String): String {
        return buildString {
            append(
                when {
                    theme.endsWith("SYSTEM") -> stringResource(R.string.settings_theme_system)
                    theme.endsWith("LIGHT") -> stringResource(R.string.settings_theme_light)
                    theme.endsWith("DARK") -> stringResource(R.string.settings_theme_dark)
                    theme.endsWith("BLACK") -> stringResource(R.string.settings_theme_black)
                    else -> stringResource(R.string.app_error)
                }
            )
            append(
                when {
                    theme.startsWith("STATIC") -> ""
                    theme.startsWith("DYNAMIC") -> " - ${stringResource(R.string.settings_theme_dynamic)}"
                    else -> " - ${stringResource(R.string.app_error)}"
                }
            )
        }
    }
}