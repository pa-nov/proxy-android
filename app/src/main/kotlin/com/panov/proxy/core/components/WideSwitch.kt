package com.panov.proxy.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.panov.proxy.core.theme.ProxyTheme

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
    Button(
        onClick = {
            onCheckedChange(!checked)
        },
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonColors(
            MaterialTheme.colorScheme.surface,
            MaterialTheme.colorScheme.onSurface,
            MaterialTheme.colorScheme.surfaceVariant,
            MaterialTheme.colorScheme.onSurfaceVariant
        ),
        contentPadding = PaddingValues(8.dp)
    ) {
        if (icon != null) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .padding(4.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .heightIn(32.dp)
                .padding(4.dp, 0.dp),
            verticalArrangement = Arrangement.Center
        ) {
            if (title != null) {
                Text(
                    text = title, style = MaterialTheme.typography.titleMedium
                )
            }
            if (description != null) {
                Text(
                    text = description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
        Switch(
            checked = checked, onCheckedChange = null, enabled = enabled, colors = SwitchColors(
                MaterialTheme.colorScheme.surfaceVariant,
                MaterialTheme.colorScheme.onSurface,
                MaterialTheme.colorScheme.onSurface,
                Color.Transparent,
                MaterialTheme.colorScheme.onSurface,
                MaterialTheme.colorScheme.surfaceVariant,
                MaterialTheme.colorScheme.surfaceVariant,
                Color.Transparent,
                MaterialTheme.colorScheme.surface,
                MaterialTheme.colorScheme.onSurfaceVariant,
                MaterialTheme.colorScheme.onSurfaceVariant,
                Color.Transparent,
                MaterialTheme.colorScheme.onSurfaceVariant,
                MaterialTheme.colorScheme.surface,
                MaterialTheme.colorScheme.surface,
                Color.Transparent,
            )
        )
    }
}

@Preview
@Composable
private fun PreviewWideSwitches() {
    ProxyTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(16.dp)),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            WideSwitch(
                checked = false, onCheckedChange = {}, title = "Title"
            )
            WideSwitch(
                checked = true, onCheckedChange = {}, title = "Title"
            )
            WideSwitch(
                checked = true, onCheckedChange = {}, enabled = false, title = "Title"
            )
            WideSwitch(
                checked = false, onCheckedChange = {}, enabled = false, title = "Title"
            )
        }
    }
}