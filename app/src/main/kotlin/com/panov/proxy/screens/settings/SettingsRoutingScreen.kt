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
import com.panov.proxy.utils.Settings
import com.panov.proxy.utils.SettingsViewModel

@Composable
fun SettingsRoutingScreen(navigator: NavHostController) {
    val settings = viewModel(SettingsViewModel::class.java)
    HeaderScreen(
        navigator = navigator, title = stringResource(R.string.title_settings_routing)
    ) {
        run {
            val region by settings.region.collectAsStateWithLifecycle()
            var showDialog by remember { mutableStateOf(false) }
            if (showDialog) {
                SelectItemDialog(
                    onDismissRequest = { showDialog = false },
                    onConfirmRequest = {
                        showDialog = false
                        settings.updateRegion(Settings.regions[it])
                    },
                    title = stringResource(R.string.settings_region),
                    items = Settings.regions.map {
                        Pair(it, null)
                    }.toTypedArray(),
                    default = Settings.regions.indexOf(region)
                )
            }
            WideButton(
                onClick = { showDialog = true },
                title = stringResource(R.string.settings_region),
                description = region
            )
        }
        run {
            val outboundDefault by settings.outboundDefault.collectAsStateWithLifecycle()
            var showDialog by remember { mutableStateOf(false) }
            if (showDialog) {
                SelectItemDialog(
                    onDismissRequest = { showDialog = false },
                    onConfirmRequest = {
                        showDialog = false
                        settings.updateOutboundDefault(Settings.outbounds[it])
                    },
                    title = stringResource(R.string.settings_outbound_default),
                    items = Settings.outbounds.map {
                        Pair(it, null)
                    }.toTypedArray(),
                    default = Settings.outbounds.indexOf(outboundDefault)
                )
            }
            WideButton(
                onClick = { showDialog = true },
                title = stringResource(R.string.settings_outbound_default),
                description = outboundDefault
            )
        }
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "use_auto_split_tunnel"
        )
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "use_auto_per_app_proxy"
        )
        run {
            val blockAdvertisements by settings.blockAdvertisements.collectAsStateWithLifecycle()
            WideSwitch(
                checked = blockAdvertisements,
                onCheckedChange = { settings.updateBlockAdvertisements(it) },
                title = stringResource(R.string.settings_block_advertisements)
            )
        }
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