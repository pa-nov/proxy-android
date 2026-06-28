package com.panov.proxy

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.layout.windowInsetsStartWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.panov.proxy.core.HeaderScreen
import com.panov.proxy.core.theme.ProxyTheme
import com.panov.proxy.screens.Routes
import com.panov.proxy.screens.about.AboutScreen
import com.panov.proxy.screens.device.DeviceScreen
import com.panov.proxy.screens.home.HomeScreen
import com.panov.proxy.screens.settings.SettingsGeneralScreen
import com.panov.proxy.screens.settings.SettingsLibrariesScreen
import com.panov.proxy.screens.settings.SettingsPrivacyScreen
import com.panov.proxy.screens.settings.SettingsRoutingScreen
import com.panov.proxy.screens.settings.SettingsScreen
import com.panov.proxy.utils.LocaleManager.applyLocaleFromSettings
import com.panov.proxy.utils.SettingsViewModel
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val settings by viewModels<SettingsViewModel>()

    val proxyLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            (application as Application).startProxy()
        }
    }

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context.applyLocaleFromSettings())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val theme by settings.theme.collectAsStateWithLifecycle()
            val navigator = rememberNavController()
            ProxyTheme(theme) {
                NavHost(
                    navController = navigator,
                    startDestination = Routes.HOME,
                    enterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left, tween(500)
                        )
                    },
                    exitTransition = {
                        ExitTransition.None
                    },
                    popEnterTransition = {
                        EnterTransition.None
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right, tween(500)
                        )
                    },
                    contentAlignment = Alignment.Center
                ) {
                    composable(Routes.HOME) {
                        HomeScreen(navigator)
                    }
                    composable(Routes.ABOUT) {
                        AboutScreen(navigator)
                    }
                    composable(Routes.DEVICE) {
                        DeviceScreen(navigator)
                    }
                    composable(Routes.CONFIGS) {
                        HeaderScreen(navigator, stringResource(R.string.title_configs)) {}
                    }
                    composable(Routes.SETTINGS) {
                        SettingsScreen(navigator)
                    }
                    composable(Routes.Settings.GENERAL) {
                        SettingsGeneralScreen(navigator)
                    }
                    composable(Routes.Settings.ROUTING) {
                        SettingsRoutingScreen(navigator)
                    }
                    composable(Routes.Settings.PRIVACY) {
                        SettingsPrivacyScreen(navigator)
                    }
                    composable(Routes.Settings.LIBRARIES) {
                        SettingsLibrariesScreen(navigator)
                    }
                }
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val shadow = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .windowInsetsBottomHeight(WindowInsets.navigationBars)
                            .background(shadow)
                            .clickable(enabled = false, onClick = {})
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .fillMaxHeight()
                            .windowInsetsStartWidth(WindowInsets.navigationBars)
                            .background(shadow)
                            .clickable(enabled = false, onClick = {})
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .fillMaxHeight()
                            .windowInsetsEndWidth(WindowInsets.navigationBars)
                            .background(shadow)
                            .clickable(enabled = false, onClick = {})
                    )
                }
            }
        }
        lifecycleScope.launch {
            settings.language.drop(1).collect {
                recreate()
            }
        }
        lifecycleScope.launch {
            settings.theme.collect {
                val isInLightTheme = when {
                    it.endsWith("BLACK") -> false
                    it.endsWith("DARK") -> false
                    it.endsWith("LIGHT") -> true
                    else -> (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) != Configuration.UI_MODE_NIGHT_YES
                }
                WindowCompat.getInsetsController(window, window.decorView).apply {
                    isAppearanceLightStatusBars = isInLightTheme
                    isAppearanceLightNavigationBars = isInLightTheme
                }
            }
        }
        onBackPressedDispatcher.addCallback {
            finish()
        }
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        window.navigationBarColor = android.graphics.Color.TRANSPARENT
        if (Build.VERSION.SDK_INT >= 33) {
            val isGranted = checkSelfPermission(
                android.Manifest.permission.POST_NOTIFICATIONS
            )
            if (isGranted != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 0
                )
            }
        }
    }
}