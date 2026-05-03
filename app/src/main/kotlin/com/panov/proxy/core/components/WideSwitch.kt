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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.shadow.Shadow
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
        onClick = { onCheckedChange(!checked) },
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(RoundedCornerShape(16.dp), Shadow(radius = 8.dp, alpha = 0.5f)),
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonColors(
            MaterialTheme.colorScheme.surface,
            MaterialTheme.colorScheme.onSurface,
            MaterialTheme.colorScheme.surfaceVariant,
            MaterialTheme.colorScheme.onSurfaceVariant
        ),
        contentPadding = PaddingValues(12.dp)
    ) {
        if (icon != null) {
            Icon(
                painter = icon, contentDescription = null, modifier = Modifier.size(32.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .heightIn(32.dp)
                .padding(if (icon != null) 8.dp else 4.dp, 0.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically)
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

@Preview
@Composable
private fun PreviewWideSwitches() {
    ProxyTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(16.dp))
                .focusProperties { canFocus = false },
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            WideSwitch(
                checked = true, onCheckedChange = {}, title = "Title"
            )
            WideSwitch(
                checked = false, onCheckedChange = {}, title = "Title"
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