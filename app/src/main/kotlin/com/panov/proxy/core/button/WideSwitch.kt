package com.panov.proxy.core.button

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun WideSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: Painter? = null,
    title: String? = null,
    description: String? = null
) {
    WideButton(
        onClick = { onCheckedChange(!checked) },
        modifier = modifier,
        enabled = enabled,
        icon = icon,
        title = title,
        description = description
    ) {
        Switch(
            checked = checked, onCheckedChange = null, enabled = enabled, colors = SwitchColors(
                MaterialTheme.colorScheme.onPrimary,
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.primary,
                Color.Transparent,
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.surface,
                MaterialTheme.colorScheme.primary,
                Color.Transparent,
                MaterialTheme.colorScheme.surfaceVariant,
                MaterialTheme.colorScheme.onSurfaceVariant,
                MaterialTheme.colorScheme.onSurfaceVariant,
                Color.Transparent,
                MaterialTheme.colorScheme.onSurfaceVariant,
                MaterialTheme.colorScheme.surfaceVariant,
                MaterialTheme.colorScheme.onSurfaceVariant,
                Color.Transparent
            )
        )
    }
}