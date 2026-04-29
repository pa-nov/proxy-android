package com.panov.proxy.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.panov.proxy.R
import com.panov.proxy.core.components.SmallButton
import com.panov.proxy.core.components.WideButton
import com.panov.proxy.core.theme.ProxyTheme
import com.panov.proxy.screens.Routes

@Composable
fun HomeScreen(navigator: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.app_placeholder),
            modifier = Modifier.align(Alignment.TopStart),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge
        )
        SmallButton(
            onClick = {
                navigator.navigate(Routes.SETTINGS)
            },
            modifier = Modifier.align(Alignment.TopEnd),
            painter = painterResource(R.drawable.icon_settings)
        )
        IconButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.Center)
                .size(160.dp),
            colors = IconButtonColors(
                MaterialTheme.colorScheme.secondaryFixed,
                MaterialTheme.colorScheme.onSecondaryFixed,
                MaterialTheme.colorScheme.secondaryFixed,
                MaterialTheme.colorScheme.onSecondaryFixed
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_power),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
        WideButton(
            onClick = {
                navigator.navigate(Routes.CONFIGS)
            },
            modifier = Modifier.align(Alignment.BottomCenter),
            title = stringResource(R.string.app_placeholder),
            isExternal = false
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