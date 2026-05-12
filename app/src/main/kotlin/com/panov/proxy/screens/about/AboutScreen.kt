package com.panov.proxy.screens.about

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.panov.proxy.R
import com.panov.proxy.core.HeaderScreen
import com.panov.proxy.core.button.WideButton
import com.panov.proxy.core.button.WideLink
import com.panov.proxy.core.theme.ProxyTheme
import com.panov.proxy.screens.Routes

@Composable
fun AboutScreen(navigator: NavHostController) {
    val context = LocalContext.current
    val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
    HeaderScreen(
        navigator = navigator, title = stringResource(R.string.title_about)
    ) {
        val version = "${packageInfo.versionName} (${packageInfo.versionCode})"
        WideButton(
            onClick = {
                context.getSystemService(ClipboardManager::class.java).setPrimaryClip(
                    ClipData.newPlainText("", version)
                )
            }, title = stringResource(R.string.about_version), description = version
        )
        val hardware = @SuppressLint("HardwareIds") Settings.Secure.getString(
            context.contentResolver, Settings.Secure.ANDROID_ID
        ).uppercase()
        WideButton(
            onClick = {
                context.getSystemService(ClipboardManager::class.java).setPrimaryClip(
                    ClipData.newPlainText("", hardware)
                )
            }, title = stringResource(R.string.about_hardware), description = hardware
        )
        WideLink(
            onClick = {
                navigator.navigate(Routes.DEVICE)
            }, title = stringResource(R.string.title_device), isExternal = false
        )
        WideLink(
            onClick = {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW, "https://github.com/pa-nov/proxy-android".toUri()
                    )
                )
            }, title = stringResource(R.string.about_source_code), isExternal = true
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