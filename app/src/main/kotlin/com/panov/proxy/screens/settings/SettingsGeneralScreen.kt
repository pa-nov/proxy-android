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
fun SettingsGeneralScreen(navigator: NavHostController) {
    HeaderScreen(
        navigator = navigator, title = stringResource(R.string.title_settings_general)
    ) {
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "language"
        )
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "theme"
        )
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "log_level"
        )
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "check_ip_after_connect"
        )
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "display_speed_in_notification"
        )
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