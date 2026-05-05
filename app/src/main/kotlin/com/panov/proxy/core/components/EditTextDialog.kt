package com.panov.proxy.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.panov.proxy.core.theme.ProxyTheme

@Composable
fun EditTextDialog(
    onDismissRequest: () -> Unit,
    onConfirmRequest: (String) -> Unit,
    title: String? = null,
    placeholder: String? = null,
    onValueChange: (String) -> Boolean = { it.isBlank() },
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    singleLine: Boolean = false,
    default: String = ""
) {
    var text by remember { mutableStateOf(default) }
    var error by remember { mutableStateOf(false) }
    CustomDialog(
        onDismissRequest = { onDismissRequest() },
        onConfirmRequest = { onConfirmRequest(text.trim()) },
        title = title
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it; error = onValueChange(text) },
            modifier = Modifier
                .weight(1f, false)
                .fillMaxWidth(),
            placeholder = { placeholder?.let { Text(it) } },
            isError = error,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = singleLine,
            shape = RoundedCornerShape(16.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewEditTextDialog() {
    ProxyTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            EditTextDialog(
                onDismissRequest = {},
                onConfirmRequest = {},
                title = "Title",
                default = "default"
            )
        }
    }
}