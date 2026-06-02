package com.panov.proxy.screens.home

import android.net.VpnService
import androidx.activity.compose.LocalActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.panov.proxy.Application
import com.panov.proxy.MainActivity
import com.panov.proxy.R
import com.panov.proxy.core.button.LargeButton
import com.panov.proxy.core.button.SmallButton
import com.panov.proxy.core.button.WideLink
import com.panov.proxy.core.theme.ProxyTheme
import com.panov.proxy.screens.Routes
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(navigator: NavHostController) {
    val activity = LocalActivity.current as MainActivity
    val powerState = getPowerState()
    val powerColor by animateColorAsState(powerState.first)
    val onPowerColor by animateColorAsState(powerState.second)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding()
            .padding(16.dp)
    ) {
        Text(
            text = powerState.third,
            modifier = Modifier.align(Alignment.TopStart),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge
        )
        SmallButton(
            onClick = { navigator.navigate(Routes.SETTINGS) },
            modifier = Modifier.align(Alignment.TopEnd),
            painter = painterResource(R.drawable.icon_settings)
        )
        LargeButton(
            onClick = {
                if (Application.isProxyEnabled.value) {
                    (activity.application as Application).stopProxy()
                } else {
                    val prepare = VpnService.prepare(activity.applicationContext)
                    if (prepare != null) {
                        activity.proxyLauncher.launch(prepare)
                    } else {
                        (activity.application as Application).startProxy()
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.Center)
                .size(176.dp),
            enabled = true,
            colors = ButtonColors(
                powerColor,
                onPowerColor,
                MaterialTheme.colorScheme.surfaceVariant,
                MaterialTheme.colorScheme.onSurfaceVariant
            ),
            painter = painterResource(R.drawable.icon_power)
        )
        WideLink(
            onClick = { navigator.navigate(Routes.CONFIGS) },
            modifier = Modifier.align(Alignment.BottomCenter),
            title = stringResource(R.string.title_configs)
        )
    }
}

@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_NO)
@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewHomeScreen() {
    ProxyTheme {
        HomeScreen(NavHostController(LocalContext.current))
    }
}


@Composable
private fun getPowerState(): Triple<Color, Color, String> {
    val isProxyEnabled by Application.isProxyEnabled.collectAsStateWithLifecycle()
    val isProxyConnected by Application.isProxyConnected.collectAsStateWithLifecycle()
    return if (isProxyEnabled) {
        if (isProxyConnected) {
            Triple(
                MaterialTheme.colorScheme.primaryFixed,
                MaterialTheme.colorScheme.onPrimaryFixed,
                stringResource(R.string.state_connected)
            )
        } else {
            var dots by remember { mutableStateOf("") }
            LaunchedEffect(Unit) {
                while (true) {
                    delay(250)
                    dots = when (dots) {
                        "" -> "."
                        "." -> ".."
                        ".." -> "..."
                        else -> ""
                    }
                }
            }
            Triple(
                MaterialTheme.colorScheme.secondaryFixed,
                MaterialTheme.colorScheme.onSecondaryFixed,
                stringResource(R.string.state_connecting) + dots
            )
        }
    } else {
        Triple(
            MaterialTheme.colorScheme.tertiaryFixed,
            MaterialTheme.colorScheme.onTertiaryFixed,
            stringResource(R.string.state_disconnected)
        )
    }
}