package com.panov.proxy.screens.settings

import android.annotation.SuppressLint
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.panov.proxy.R
import com.panov.proxy.core.HeaderScreen
import com.panov.proxy.core.button.WideButton
import com.panov.proxy.core.button.WideSwitch
import com.panov.proxy.core.dialog.SelectItemDialog
import com.panov.proxy.core.theme.ProxyTheme
import com.panov.proxy.utils.LocaleManager
import com.panov.proxy.utils.LocaleManager.applyLocale
import com.panov.proxy.utils.Settings
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun SettingsGeneralScreen(navigator: NavHostController) {
    val activity = LocalActivity.current
    val context = LocalContext.current
    val settings = remember(context) { Settings(context) }
    val coroutine = rememberCoroutineScope()
    HeaderScreen(
        navigator = navigator, title = stringResource(R.string.title_settings_general)
    ) {
        run {
            val language by settings.getData(
                Settings.General.LANGUAGE, Settings.languages[0]
            ).collectAsState(Settings.languages[0], coroutine.coroutineContext)
            var showDialog by remember { mutableStateOf(false) }
            if (showDialog) {
                SelectItemDialog(
                    onDismissRequest = { showDialog = false },
                    onConfirmRequest = {
                        showDialog = false
                        coroutine.launch {
                            settings.setData(
                                Settings.General.LANGUAGE, Settings.languages[it]
                            )
                            activity?.recreate()
                        }
                    },
                    title = stringResource(R.string.settings_language),
                    items = Settings.languages.map {
                        Pair(getLanguageText(it, language), getLanguageText(it))
                    }.toTypedArray(),
                    default = Settings.languages.indexOf(language)
                )
            }
            WideButton(
                onClick = { showDialog = true },
                title = stringResource(R.string.settings_language),
                description = getLanguageText(language)
            )
        }
        run {
            val theme by settings.getData(
                Settings.General.THEME, Settings.themes[0]
            ).collectAsState(Settings.themes[0], coroutine.coroutineContext)
            var showDialog by remember { mutableStateOf(false) }
            if (showDialog) {
                SelectItemDialog(
                    onDismissRequest = { showDialog = false },
                    onConfirmRequest = {
                        showDialog = false
                        coroutine.launch {
                            settings.setData(
                                Settings.General.THEME, Settings.themes[it]
                            )
                        }
                    },
                    title = stringResource(R.string.settings_theme),
                    items = Settings.themes.map {
                        Pair(getThemeText(it), null)
                    }.toTypedArray(),
                    default = Settings.themes.indexOf(theme)
                )
            }
            WideButton(
                onClick = { showDialog = true },
                title = stringResource(R.string.settings_theme),
                description = getThemeText(theme)
            )
        }
        run {
            val log by settings.getData(
                Settings.General.LOG, Settings.logs[0]
            ).collectAsState(Settings.logs[0], coroutine.coroutineContext)
            var showDialog by remember { mutableStateOf(false) }
            if (showDialog) {
                SelectItemDialog(
                    onDismissRequest = { showDialog = false },
                    onConfirmRequest = {
                        showDialog = false
                        coroutine.launch {
                            settings.setData(
                                Settings.General.LOG, Settings.logs[it]
                            )
                        }
                    },
                    title = stringResource(R.string.settings_log),
                    items = Settings.logs.map {
                        Pair(it, null)
                    }.toTypedArray(),
                    default = Settings.logs.indexOf(log)
                )
            }
            WideButton(
                onClick = { showDialog = true },
                title = stringResource(R.string.settings_log),
                description = log
            )
        }
        run {
            val autoCheckIP by settings.getData(
                Settings.General.AUTO_CHECK_IP, false
            ).collectAsState(false, coroutine.coroutineContext)
            WideSwitch(
                checked = autoCheckIP, onCheckedChange = {
                    coroutine.launch {
                        settings.setData(
                            Settings.General.AUTO_CHECK_IP, it
                        )
                    }
                }, title = stringResource(R.string.settings_auto_check_ip)
            )
        }
        run {
            val displaySpeed by settings.getData(
                Settings.General.DISPLAY_SPEED, false
            ).collectAsState(false, coroutine.coroutineContext)
            WideSwitch(
                checked = displaySpeed, onCheckedChange = {
                    coroutine.launch {
                        settings.setData(
                            Settings.General.DISPLAY_SPEED, it
                        )
                    }
                }, title = stringResource(R.string.settings_display_speed)
            )
        }
    }
}

@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_NO)
@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewSettingsGeneralScreen() {
    ProxyTheme {
        SettingsGeneralScreen(NavHostController(LocalContext.current))
    }
}


@Composable
private fun getLanguageText(language: String, locale: String = language): String {
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
private fun getThemeText(theme: String): String {
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