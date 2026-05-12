package com.panov.proxy.core.button

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.panov.proxy.R

@Composable
fun WideLink(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: Painter? = null,
    title: String? = null,
    description: String? = null,
    isExternal: Boolean = false
) {
    WideButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        icon = icon,
        title = title,
        description = description
    ) {
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