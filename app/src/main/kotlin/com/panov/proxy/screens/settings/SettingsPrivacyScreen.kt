package com.panov.proxy.screens.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.panov.proxy.R
import com.panov.proxy.core.HeaderScreen
import com.panov.proxy.core.button.WideButton
import com.panov.proxy.core.button.WideSwitch
import com.panov.proxy.core.dialog.EditTextDialog
import com.panov.proxy.core.theme.ProxyTheme
import com.panov.proxy.utils.Settings
import kotlinx.coroutines.launch

@Composable
fun SettingsPrivacyScreen(navigator: NavHostController) {
    val context = LocalContext.current
    val settings = remember(context) { Settings(context) }
    val coroutine = rememberCoroutineScope()
    HeaderScreen(
        navigator = navigator, title = stringResource(R.string.title_settings_privacy)
    ) {
        run {
            val xrayPortUseCustom by settings.getData(
                Settings.Privacy.XRAY_PORT_USE_CUSTOM, false
            ).collectAsState(false, coroutine.coroutineContext)
            val xrayPortCustom by settings.getData(
                Settings.Privacy.XRAY_PORT_CUSTOM, 0
            ).collectAsState(0, coroutine.coroutineContext)
            var showDialog by remember { mutableStateOf(false) }
            if (showDialog) {
                EditTextDialog(
                    onDismissRequest = { showDialog = false },
                    onConfirmRequest = {
                        if (it.toUShortOrNull() != null) {
                            showDialog = false
                            coroutine.launch {
                                settings.setData(
                                    Settings.Privacy.XRAY_PORT_CUSTOM, it.toInt()
                                )
                            }
                        }
                    },
                    title = stringResource(R.string.settings_xray_port_custom),
                    placeholder = "${UShort.MIN_VALUE} - ${UShort.MAX_VALUE}",
                    onValueChange = { it.toUShortOrNull() == null },
                    keyboardType = KeyboardType.Number,
                    singleLine = true,
                    default = xrayPortCustom.toString()
                )
            }
            WideSwitch(
                checked = xrayPortUseCustom, onCheckedChange = {
                    coroutine.launch {
                        settings.setData(
                            Settings.Privacy.XRAY_PORT_USE_CUSTOM, it
                        )
                    }
                }, title = stringResource(R.string.settings_xray_port_use_custom)
            )
            WideButton(
                onClick = { showDialog = true },
                enabled = xrayPortUseCustom,
                title = stringResource(R.string.settings_xray_port_custom),
                description = if (xrayPortUseCustom) xrayPortCustom.toString()
                else stringResource(R.string.settings_port_random)
            )
        }
        run {
            val sendHWID by settings.getData(
                Settings.Privacy.SEND_HWID, false
            ).collectAsState(false, coroutine.coroutineContext)
            WideSwitch(
                checked = sendHWID, onCheckedChange = {
                    coroutine.launch {
                        settings.setData(
                            Settings.Privacy.SEND_HWID, it
                        )
                    }
                }, title = stringResource(R.string.settings_send_hwid)
            )
        }
    }
}

@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_NO)
@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewSettingsPrivacyScreen() {
    ProxyTheme {
        SettingsPrivacyScreen(NavHostController(LocalContext.current))
    }
}