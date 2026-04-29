package com.panov.proxy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.panov.proxy.core.components.HeaderScreen
import com.panov.proxy.core.theme.ProxyTheme
import com.panov.proxy.screens.Routes
import com.panov.proxy.screens.home.HomeScreen
import com.panov.proxy.screens.settings.SettingsGeneralScreen
import com.panov.proxy.screens.settings.SettingsLibrariesScreen
import com.panov.proxy.screens.settings.SettingsPrivacyScreen
import com.panov.proxy.screens.settings.SettingsRoutingScreen
import com.panov.proxy.screens.settings.SettingsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProxyTheme {
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
                        HeaderScreen(
                            navigator, stringResource(R.string.title_about)
                        ) {}
                    }
                    composable(Routes.DEVICE) {
                        HeaderScreen(
                            navigator, stringResource(R.string.title_device)
                        ) {}
                    }
                    composable(Routes.CONFIGS) {
                        HeaderScreen(
                            navigator, stringResource(R.string.title_configs)
                        ) {}
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
    }
}