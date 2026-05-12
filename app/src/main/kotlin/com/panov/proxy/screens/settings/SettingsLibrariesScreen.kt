package com.panov.proxy.screens.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.panov.proxy.R
import com.panov.proxy.core.HeaderScreen
import com.panov.proxy.core.button.WideButton
import com.panov.proxy.core.theme.ProxyTheme

@Composable
fun SettingsLibrariesScreen(navigator: NavHostController) {
    HeaderScreen(
        navigator = navigator, title = stringResource(R.string.title_settings_libraries)
    ) {
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "xray_version_and_update"
        )
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "turn_version_and_update"
        )
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "geoip_version_and_update"
        )
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "geosite_version_and_update"
        )
    }
}

@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_NO)
@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewSettingsLibrariesScreen() {
    ProxyTheme {
        SettingsLibrariesScreen(NavHostController(LocalContext.current))
    }
}