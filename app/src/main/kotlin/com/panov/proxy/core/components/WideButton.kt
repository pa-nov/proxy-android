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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.panov.proxy.R
import com.panov.proxy.core.theme.ProxyTheme

@Composable
fun WideButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: Painter? = null,
    title: String? = null,
    description: String? = null,
    isExternal: Boolean? = null
) {
    Button(
        onClick = onClick,
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
        if (isExternal != null) {
            Icon(
                painter = painterResource(
                    if (isExternal) {
                        R.drawable.icon_arrow_outward
                    } else {
                        R.drawable.icon_arrow_right
                    }
                ), contentDescription = null, modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewWideButtons() {
    ProxyTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(16.dp)),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            WideButton({})
            WideButton(
                onClick = {}, title = "Title"
            )
            WideButton(
                onClick = {}, title = "Title", description = "Description"
            )
            WideButton(
                onClick = {},
                icon = painterResource(R.drawable.icon_add),
                title = "Title",
                description = "Description"
            )
            WideButton(
                onClick = {},
                icon = painterResource(R.drawable.icon_add),
                title = "Title",
                description = "Description",
                isExternal = false
            )
            WideButton(
                onClick = {},
                enabled = false,
                icon = painterResource(R.drawable.icon_add),
                title = "Title",
                description = "Description",
                isExternal = false
            )
        }
    }
}