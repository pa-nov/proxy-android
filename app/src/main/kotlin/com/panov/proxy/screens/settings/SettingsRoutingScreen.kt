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
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.panov.proxy.R
import com.panov.proxy.core.components.HeaderScreen
import com.panov.proxy.core.components.SelectItemDialog
import com.panov.proxy.core.components.WideButton
import com.panov.proxy.core.components.WideSwitch
import com.panov.proxy.core.theme.ProxyTheme
import com.panov.proxy.utils.Settings
import kotlinx.coroutines.launch

@Composable
fun SettingsRoutingScreen(navigator: NavHostController) {
    val context = LocalContext.current
    val settings = remember(context) { Settings(context) }
    val coroutine = rememberCoroutineScope()
    HeaderScreen(
        navigator = navigator, title = stringResource(R.string.title_settings_routing)
    ) {
        run {
            val region by settings.getData(
                Settings.Routing.REGION, Settings.regions[0]
            ).collectAsState(Settings.regions[0], coroutine.coroutineContext)
            var showDialog by remember { mutableStateOf(false) }
            if (showDialog) {
                SelectItemDialog(
                    onDismissRequest = { showDialog = false },
                    onConfirmRequest = {
                        showDialog = false
                        coroutine.launch {
                            settings.setData(
                                Settings.Routing.REGION, Settings.regions[it]
                            )
                        }
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
            val outboundDefault by settings.getData(
                Settings.Routing.OUTBOUND_DEFAULT, Settings.outbounds[0]
            ).collectAsState(Settings.outbounds[0], coroutine.coroutineContext)
            var showDialog by remember { mutableStateOf(false) }
            if (showDialog) {
                SelectItemDialog(
                    onDismissRequest = { showDialog = false },
                    onConfirmRequest = {
                        showDialog = false
                        coroutine.launch {
                            settings.setData(
                                Settings.Routing.OUTBOUND_DEFAULT, Settings.outbounds[it]
                            )
                        }
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
            val blockAdvertisements by settings.getData(
                Settings.Routing.BLOCK_ADVERTISEMENTS, false
            ).collectAsState(false, coroutine.coroutineContext)
            WideSwitch(
                checked = blockAdvertisements, onCheckedChange = {
                    coroutine.launch {
                        settings.setData(
                            Settings.Routing.BLOCK_ADVERTISEMENTS, it
                        )
                    }
                }, title = stringResource(R.string.settings_block_advertisements)
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