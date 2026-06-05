package com.panov.proxy.screens.device

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.panov.proxy.R
import com.panov.proxy.core.HeaderScreen
import com.panov.proxy.core.theme.ProxyTheme
import com.panov.proxy.utils.ExportManager
import java.util.Locale

@Composable
fun DeviceScreen(navigator: NavHostController) {
    val resources = LocalResources.current
    val context = LocalContext.current
    val window = LocalWindowInfo.current
    val config = LocalConfiguration.current
    val title = stringResource(R.string.title_device)
    val info = buildString {
        appendLine("Brand: ${Build.MANUFACTURER}")
        appendLine("Model: ${Build.MODEL}")
        appendLine("Mobile Country Code: ${config.mcc}")
        appendLine("Mobile Network Code: ${config.mnc}")
        appendLine()
        appendLine("System Language : ${@SuppressLint("NonObservableLocale") Locale.getDefault().language}")
        appendLine("System Theme    : ${if (isSystemInDarkTheme()) "dark" else "light"}")
        appendLine()
        appendLine(
            "Orientation   : ${
                when (config.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> "landscape"
                    Configuration.ORIENTATION_PORTRAIT -> "portrait"
                    else -> "undefined"
                }
            }"
        )
        appendLine("Window Width  : ${window.containerSize.width}px | ${window.containerDpSize.width.value.toInt()}dp")
        appendLine("Window Height : ${window.containerSize.height}px | ${window.containerDpSize.height.value.toInt()}dp")
        appendLine()
        appendLine("Font Scale  : ${config.fontScale}")
        if (Build.VERSION.SDK_INT >= 31) {
            appendLine("Font Weight : ${config.fontWeightAdjustment}")
        }
        appendLine("Density     : ${resources.displayMetrics.density}")
        appendLine("Density DPI : ${resources.displayMetrics.densityDpi}")
        appendLine()
        appendLine("Android : ${Build.VERSION.RELEASE}")
        appendLine("API/SDK : ${Build.VERSION.SDK_INT}")
        appendLine("ABIs: ${Build.SUPPORTED_ABIS.joinToString(" | ")}")
    }
    HeaderScreen(
        navigator = navigator, title = title, moreMenu = buildList {
            add(Pair(stringResource(R.string.action_copy)) {
                ExportManager.copyToClipboard(
                    context, info, title
                )
            })
            if (Build.VERSION.SDK_INT >= 29) {
                add(Pair(stringResource(R.string.action_save)) {
                    ExportManager.saveToDownloads(
                        context, info, "device.txt", ExportManager.MIME_TYPE_TEXT
                    )
                })
            }
        }.toTypedArray()
    ) {
        SelectionContainer {
            Text(
                text = info,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_NO)
@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewDeviceScreen() {
    ProxyTheme {
        DeviceScreen(NavHostController(LocalContext.current))
    }
}