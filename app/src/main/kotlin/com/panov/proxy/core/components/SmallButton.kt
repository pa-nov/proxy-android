package com.panov.proxy.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RippleConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.panov.proxy.R
import com.panov.proxy.core.theme.ProxyTheme

@Composable
fun SmallButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    painter: Painter
) {
    CompositionLocalProvider(
        value = LocalRippleConfiguration provides RippleConfiguration(
            color = MaterialTheme.colorScheme.onSurface
        )
    ) {
        IconButton(
            onClick = onClick,
            modifier = modifier.size(40.dp),
            enabled = enabled,
            colors = IconButtonColors(
                Color.Transparent,
                MaterialTheme.colorScheme.onSurface,
                Color.Transparent,
                MaterialTheme.colorScheme.onSurfaceVariant
            )
        ) {
            Icon(
                painter = painter, contentDescription = null, modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSmallButtons() {
    ProxyTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(16.dp))
                .focusProperties { canFocus = false },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SmallButton(
                onClick = {}, painter = painterResource(R.drawable.icon_add)
            )
            SmallButton(
                onClick = {}, enabled = false, painter = painterResource(R.drawable.icon_add)
            )
            SmallButton(
                onClick = {}, painter = painterResource(R.drawable.icon_add)
            )
        }
    }
}