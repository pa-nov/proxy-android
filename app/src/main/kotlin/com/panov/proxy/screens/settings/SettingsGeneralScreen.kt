package com.panov.proxy.screens.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.panov.proxy.R
import com.panov.proxy.core.HeaderScreen
import com.panov.proxy.core.button.WideButton
import com.panov.proxy.core.button.WideSwitch
import com.panov.proxy.core.dialog.SelectItemDialog
import com.panov.proxy.core.theme.ProxyTheme
import com.panov.proxy.utils.ComposeUtils.getLanguageText
import com.panov.proxy.utils.ComposeUtils.getThemeText
import com.panov.proxy.utils.Settings
import com.panov.proxy.utils.SettingsViewModel

@Composable
fun SettingsGeneralScreen(navigator: NavHostController) {
    val settings = viewModel(SettingsViewModel::class.java)
    HeaderScreen(
        navigator = navigator, title = stringResource(R.string.title_settings_general)
    ) {
        run {
            val language by settings.language.collectAsStateWithLifecycle()
            var showDialog by remember { mutableStateOf(false) }
            if (showDialog) {
                SelectItemDialog(
                    onDismissRequest = { showDialog = false },
                    onConfirmRequest = {
                        showDialog = false
                        settings.updateLanguage(Settings.languages[it])
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
            val theme by settings.theme.collectAsStateWithLifecycle()
            var showDialog by remember { mutableStateOf(false) }
            if (showDialog) {
                SelectItemDialog(
                    onDismissRequest = { showDialog = false },
                    onConfirmRequest = {
                        showDialog = false
                        settings.updateTheme(Settings.themes[it])
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
            val log by settings.log.collectAsStateWithLifecycle()
            var showDialog by remember { mutableStateOf(false) }
            if (showDialog) {
                SelectItemDialog(
                    onDismissRequest = { showDialog = false },
                    onConfirmRequest = {
                        showDialog = false
                        settings.updateLog(Settings.logs[it])
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
            val autoCheckIP by settings.autoCheckIP.collectAsStateWithLifecycle()
            WideSwitch(
                checked = autoCheckIP,
                onCheckedChange = { settings.updateAutoCheckIP(it) },
                title = stringResource(R.string.settings_auto_check_ip)
            )
        }
        run {
            val displaySpeed by settings.displaySpeed.collectAsStateWithLifecycle()
            WideSwitch(
                checked = displaySpeed,
                onCheckedChange = { settings.updateDisplaySpeed(it) },
                title = stringResource(R.string.settings_display_speed)
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