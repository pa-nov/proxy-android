package com.panov.proxy.screens.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.panov.proxy.R
import com.panov.proxy.core.components.HeaderScreen
import com.panov.proxy.core.components.WideButton
import com.panov.proxy.core.theme.ProxyTheme

@Composable
fun SettingsPrivacyScreen(navigator: NavHostController) {
    HeaderScreen(
        navigator = navigator, title = stringResource(R.string.title_settings_privacy)
    ) {
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "use_manual_port_for_xray"
        )
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "manual_port_for_xray"
        )
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "use_manual_port_for_turn"
        )
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "manual_port_for_turn"
        )
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "send_hwid"
        )
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