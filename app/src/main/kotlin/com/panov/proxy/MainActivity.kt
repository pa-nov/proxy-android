package com.panov.proxy

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
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

class MainActivity : ComponentActivity() {
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
            val settings = viewModel(SettingsViewModel::class.java)
            val language = settings.language
            val theme by settings.theme.collectAsStateWithLifecycle()
            val inLightTheme = when {
                theme.endsWith("BLACK") -> false
                theme.endsWith("DARK") -> false
                theme.endsWith("LIGHT") -> true
                else -> !isSystemInDarkTheme()
            }
            LaunchedEffect(Unit) {
                language.drop(1).collect { recreate() }
            }
            SideEffect {
                WindowCompat.getInsetsController(
                    window, window.decorView
                ).isAppearanceLightStatusBars = inLightTheme
            }
            ProxyTheme(theme) {
                val navigator = rememberNavController()
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
            }
        }
        onBackPressedDispatcher.addCallback { finish() }
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