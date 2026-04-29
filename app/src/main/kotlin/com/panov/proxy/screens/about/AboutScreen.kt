package com.panov.proxy.screens.about

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.panov.proxy.R
import com.panov.proxy.core.components.HeaderScreen
import com.panov.proxy.core.components.WideButton
import com.panov.proxy.core.theme.ProxyTheme
import com.panov.proxy.screens.Routes

@Composable
fun AboutScreen(navigator: NavHostController) {
    val context = LocalContext.current
    HeaderScreen(
        navigator = navigator, title = stringResource(R.string.title_about)
    ) {
        WideButton(
            onClick = {},
            title = stringResource(R.string.app_placeholder),
            description = "app_version_and_update"
        )
        WideButton(
            onClick = { navigator.navigate(Routes.DEVICE) },
            title = stringResource(R.string.title_device),
            isExternal = false
        )
        WideButton(
            onClick = {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW, "https://github.com/pa-nov/proxy-android".toUri()
                    )
                )
            },
            title = stringResource(R.string.app_placeholder),
            description = "source_code_link (work)",
            isExternal = true
        )
    }
}

@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_NO)
@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewAboutScreen() {
    ProxyTheme {
        AboutScreen(NavHostController(LocalContext.current))
    }
}