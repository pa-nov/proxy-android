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
                        HeaderScreen(
                            navigator, stringResource(R.string.title_settings)
                        ) {}
                    }
                    composable(Routes.Settings.GENERAL) {
                        HeaderScreen(
                            navigator, stringResource(R.string.title_settings_general)
                        ) {}
                    }
                    composable(Routes.Settings.ROUTING) {
                        HeaderScreen(
                            navigator, stringResource(R.string.title_settings_routing)
                        ) {}
                    }
                    composable(Routes.Settings.PRIVACY) {
                        HeaderScreen(
                            navigator, stringResource(R.string.title_settings_privacy)
                        ) {}
                    }
                    composable(Routes.Settings.LIBRARIES) {
                        HeaderScreen(
                            navigator, stringResource(R.string.title_settings_libraries)
                        ) {}
                    }
                }
            }
        }
    }
}