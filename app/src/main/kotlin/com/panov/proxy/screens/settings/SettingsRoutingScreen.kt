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
fun SettingsRoutingScreen(navigator: NavHostController) {
    HeaderScreen(
        navigator = navigator, title = stringResource(R.string.title_settings_routing)
    ) {
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "default_outbound"
        )
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "region"
        )
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "use_auto_per_app_proxy"
        )
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "use_auto_split_tunnel"
        )
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "block_advertisements"
        )
    }
}

@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_NO)
@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewSettingsRoutingScreen() {
    ProxyTheme {
        SettingsRoutingScreen(NavHostController(LocalContext.current))
    }
}