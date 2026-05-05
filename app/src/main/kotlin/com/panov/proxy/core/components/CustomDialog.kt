package com.panov.proxy.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.panov.proxy.R
import com.panov.proxy.core.theme.ProxyTheme

@Composable
fun CustomDialog(
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit,
    title: String? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(32.dp),
            colors = CardColors(
                MaterialTheme.colorScheme.surfaceContainer,
                MaterialTheme.colorScheme.onSurface,
                MaterialTheme.colorScheme.surfaceContainer,
                MaterialTheme.colorScheme.onSurfaceVariant
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (title != null) {
                    Text(
                        text = title,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                content()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    arrayOf(
                        Pair(stringResource(R.string.action_dismiss), onDismissRequest),
                        Pair(stringResource(R.string.action_confirm), onConfirmRequest)
                    ).forEach {
                        TextButton(
                            onClick = it.second,
                            modifier = Modifier.weight(1f),
                            shape = CircleShape,
                            colors = ButtonColors(
                                Color.Transparent,
                                MaterialTheme.colorScheme.primary,
                                Color.Transparent,
                                MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            Text(
                                text = it.first, style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCustomDialog() {
    ProxyTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CustomDialog(
                onDismissRequest = {}, onConfirmRequest = {}, title = "Title"
            ) {
                WideButton({})
            }
        }
    }
}