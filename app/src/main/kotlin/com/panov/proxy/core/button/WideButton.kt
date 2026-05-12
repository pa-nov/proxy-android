package com.panov.proxy.core.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.dp

@Composable
fun WideButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: Painter? = null,
    title: String? = null,
    description: String? = null,
    content: @Composable (RowScope.() -> Unit) = {}
) {
    Button(
        onClick = onClick,
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
        content()
    }
}