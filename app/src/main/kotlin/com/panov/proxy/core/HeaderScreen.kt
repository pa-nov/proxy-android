package com.panov.proxy.core

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.visible
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.panov.proxy.R
import com.panov.proxy.core.button.SmallButton

@Composable
fun HeaderScreen(
    navigator: NavHostController,
    title: String = "",
    moreMenu: Array<Pair<String, (() -> Unit)?>> = emptyArray(),
    spacing: Dp? = null,
    content: @Composable (ColumnScope.() -> Unit)
) {
    val scrollState = rememberScrollState()
    val isScrolling by remember {
        derivedStateOf {
            scrollState.value > 0
        }
    }
    val headerColor by animateColorAsState(
        targetValue = if (isScrolling) {
            MaterialTheme.colorScheme.surfaceVariant
        } else {
            Color.Transparent
        }
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(headerColor)
                .statusBarsPadding()
                .padding(16.dp)
        ) {
            SmallButton(
                onClick = { navigator.popBackStack() },
                painter = painterResource(R.drawable.icon_arrow_left)
            )
            Text(
                text = title,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
            if (moreMenu.isNotEmpty()) {
                var expanded by remember { mutableStateOf(false) }
                Box {
                    SmallButton(
                        onClick = { expanded = true },
                        painter = painterResource(R.drawable.icon_more)
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                    ) {
                        moreMenu.forEach { item ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = item.first,
                                        modifier = Modifier.padding(8.dp),
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                },
                                onClick = {
                                    expanded = false
                                    item.second?.invoke()
                                },
                                enabled = moreMenu.isNotEmpty() && item.second != null,
                                colors = MenuItemColors(
                                    MaterialTheme.colorScheme.onSurface,
                                    MaterialTheme.colorScheme.onSurface,
                                    MaterialTheme.colorScheme.onSurface,
                                    MaterialTheme.colorScheme.onSurfaceVariant,
                                    MaterialTheme.colorScheme.onSurfaceVariant,
                                    MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        }
                    }
                }
            } else {
                SmallButton(
                    onClick = {},
                    modifier = Modifier.visible(false),
                    enabled = false,
                    painter = painterResource(R.drawable.icon_more)
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(spacing ?: 16.dp),
            content = content
        )
    }
}