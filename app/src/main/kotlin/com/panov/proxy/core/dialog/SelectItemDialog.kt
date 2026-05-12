package com.panov.proxy.core.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun SelectItemDialog(
    onDismissRequest: () -> Unit,
    onConfirmRequest: (Int) -> Unit,
    title: String? = null,
    items: Array<Pair<String?, String?>>,
    default: Int = 0
) {
    val scrollState = rememberScrollState()
    var selectedItem by remember { mutableIntStateOf(default) }
    CustomDialog(
        onDismissRequest = { onDismissRequest() },
        onConfirmRequest = { onConfirmRequest(selectedItem) },
        title = title
    ) {
        if (items.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .weight(1f, false)
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
            ) {
                for (item in items.indices) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val interaction = remember { MutableInteractionSource() }
                        RadioButton(
                            selected = item == selectedItem,
                            onClick = { selectedItem = item },
                            colors = RadioButtonColors(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.onSurfaceVariant,
                                MaterialTheme.colorScheme.onSurfaceVariant,
                                MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            interactionSource = interaction
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(0.dp, 12.dp)
                                .clickable(
                                    interactionSource = interaction,
                                    indication = null,
                                    role = Role.RadioButton
                                ) { selectedItem = item },
                            verticalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            items[item].first?.let {
                                Text(
                                    text = it,
                                    modifier = Modifier.fillMaxWidth(),
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                            items[item].second?.let {
                                Text(
                                    text = it,
                                    modifier = Modifier.fillMaxWidth(),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    style = MaterialTheme.typography.titleSmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}