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
import com.panov.proxy.screens.Routes

@Composable
fun SettingsScreen(navigator: NavHostController) {
    HeaderScreen(
        navigator = navigator, title = stringResource(R.string.title_settings)
    ) {
        WideButton(
            onClick = { navigator.navigate(Routes.Settings.GENERAL) },
            title = stringResource(R.string.title_settings_general),
            isExternal = false
        )
        WideButton(
            onClick = { navigator.navigate(Routes.Settings.ROUTING) },
            title = stringResource(R.string.title_settings_routing),
            isExternal = false
        )
        WideButton(
            onClick = { navigator.navigate(Routes.Settings.PRIVACY) },
            title = stringResource(R.string.title_settings_privacy),
            isExternal = false
        )
        WideButton(
            onClick = { navigator.navigate(Routes.Settings.LIBRARIES) },
            title = stringResource(R.string.title_settings_libraries),
            isExternal = false
        )
        WideButton(
            onClick = { navigator.navigate(Routes.ABOUT) },
            title = stringResource(R.string.title_about),
            isExternal = false
        )
    }
}

@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_NO)
@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewSettingsScreen() {
    ProxyTheme {
        SettingsScreen(NavHostController(LocalContext.current))
    }
}