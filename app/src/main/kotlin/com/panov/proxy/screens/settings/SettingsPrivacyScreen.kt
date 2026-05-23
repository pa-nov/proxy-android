package com.panov.proxy.screens.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.panov.proxy.R
import com.panov.proxy.core.HeaderScreen
import com.panov.proxy.core.button.WideButton
import com.panov.proxy.core.button.WideSwitch
import com.panov.proxy.core.dialog.EditTextDialog
import com.panov.proxy.core.theme.ProxyTheme
import com.panov.proxy.utils.SettingsViewModel

@Composable
fun SettingsPrivacyScreen(navigator: NavHostController) {
    val settings = viewModel(SettingsViewModel::class.java)
    HeaderScreen(
        navigator = navigator, title = stringResource(R.string.title_settings_privacy)
    ) {
        run {
            val xrayPortUseCustom by settings.xrayPortUseCustom.collectAsStateWithLifecycle()
            val xrayPortCustom by settings.xrayPortCustom.collectAsStateWithLifecycle()
            var showDialog by remember { mutableStateOf(false) }
            if (showDialog) {
                EditTextDialog(
                    onDismissRequest = { showDialog = false },
                    onConfirmRequest = {
                        if (it.toUShortOrNull() != null) {
                            showDialog = false
                            settings.updateXrayPortCustom(it.toInt())
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
                checked = xrayPortUseCustom,
                onCheckedChange = { settings.updateXrayPortUseCustom(it) },
                title = stringResource(R.string.settings_xray_port_use_custom)
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
            val sendHWID by settings.sendHWID.collectAsStateWithLifecycle()
            WideSwitch(
                checked = sendHWID,
                onCheckedChange = { settings.updateSendHWID(it) },
                title = stringResource(R.string.settings_send_hwid)
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