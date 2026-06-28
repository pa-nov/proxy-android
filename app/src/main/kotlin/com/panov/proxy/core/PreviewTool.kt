package com.panov.proxy.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.panov.proxy.R
import com.panov.proxy.core.button.LargeButton
import com.panov.proxy.core.button.SmallButton
import com.panov.proxy.core.button.WideButton
import com.panov.proxy.core.button.WideLink
import com.panov.proxy.core.button.WideSwitch
import com.panov.proxy.core.dialog.CustomDialog
import com.panov.proxy.core.dialog.EditTextDialog
import com.panov.proxy.core.dialog.SelectItemDialog
import com.panov.proxy.core.theme.ProxyTheme

@Composable
private fun PreviewScreen(content: @Composable (BoxScope.() -> Unit)) {
    ProxyTheme {
        Box(
            modifier = Modifier.fillMaxSize(), content = content
        )
    }
}

@Composable
private fun PreviewColumn(content: @Composable (ColumnScope.() -> Unit)) {
    ProxyTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .focusProperties { canFocus = false },
            verticalArrangement = Arrangement.spacedBy(16.dp),
            content = content
        )
    }
}

@Composable
private fun PreviewRow(content: @Composable (RowScope.() -> Unit)) {
    ProxyTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .focusProperties { canFocus = false },
            horizontalArrangement = Arrangement.SpaceBetween,
            content = content
        )
    }
}


@Preview
@Composable
private fun PreviewLargeButton() {
    PreviewRow {
        LargeButton(
            onClick = {},
            modifier = Modifier.size(128.dp),
            enabled = true,
            painter = painterResource(R.drawable.icon_power)
        )
        LargeButton(
            onClick = {},
            modifier = Modifier.size(128.dp),
            enabled = false,
            painter = painterResource(R.drawable.icon_power)
        )
    }
}

@Preview
@Composable
private fun PreviewSmallButton() {
    PreviewRow {
        SmallButton(
            onClick = {}, painter = painterResource(R.drawable.icon_add)
        )
        SmallButton(
            onClick = {}, painter = painterResource(R.drawable.icon_delete)
        )
        SmallButton(
            onClick = {}, enabled = false, painter = painterResource(R.drawable.icon_add)
        )
        SmallButton(
            onClick = {}, enabled = false, painter = painterResource(R.drawable.icon_delete)
        )
    }
}

@Preview
@Composable
private fun PreviewWideButton() {
    PreviewColumn {
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
            enabled = false,
            icon = painterResource(R.drawable.icon_add),
            title = "Title",
            description = "Description"
        )
    }
}

@Preview
@Composable
private fun PreviewWideLink() {
    PreviewColumn {
        WideLink({})
        WideLink(
            onClick = {}, title = "Title"
        )
        WideLink(
            onClick = {}, title = "Title", description = "Description"
        )
        WideLink(
            onClick = {},
            icon = painterResource(R.drawable.icon_add),
            title = "Title",
            description = "Description"
        )
        WideLink(
            onClick = {},
            enabled = false,
            icon = painterResource(R.drawable.icon_add),
            title = "Title",
            description = "Description",
            isExternal = true
        )
    }
}

@Preview
@Composable
private fun PreviewWideSwitch() {
    PreviewColumn {
        WideSwitch(checked = false, onCheckedChange = {})
        WideSwitch(
            checked = true,
            onCheckedChange = {},
            icon = painterResource(R.drawable.icon_add),
            title = "Title",
            description = "Description"
        )
        WideSwitch(
            checked = false,
            onCheckedChange = {},
            icon = painterResource(R.drawable.icon_add),
            title = "Title",
            description = "Description"
        )
        WideSwitch(
            checked = true,
            onCheckedChange = {},
            enabled = false,
            icon = painterResource(R.drawable.icon_add),
            title = "Title",
            description = "Description"
        )
        WideSwitch(
            checked = false,
            onCheckedChange = {},
            enabled = false,
            icon = painterResource(R.drawable.icon_add),
            title = "Title",
            description = "Description"
        )
    }
}


@Preview
@Composable
private fun PreviewCustomDialog() {
    PreviewScreen {
        CustomDialog(
            onDismissRequest = {}, onConfirmRequest = {}, title = "Title"
        ) {
            repeat(2) {
                WideButton({})
            }
        }
    }
}

@Preview
@Composable
private fun PreviewEditTextDialog() {
    PreviewScreen {
        EditTextDialog(
            onDismissRequest = {},
            onConfirmRequest = {},
            title = "Title",
            placeholder = "placeholder"
        )
    }
}

@Preview
@Composable
private fun PreviewSelectItemDialog() {
    PreviewScreen {
        SelectItemDialog(
            onDismissRequest = {},
            onConfirmRequest = {},
            title = "Title",
            items = arrayOf(
                Pair("Title", "Description"),
                Pair("Title", "Description"),
                Pair("Title", "Description"),
                Pair("Title", "Description")
            ),
            default = 1
        )
    }
}


@Preview
@Composable
private fun PreviewHeaderScreen() {
    PreviewScreen {
        HeaderScreen(
            navigator = NavHostController(LocalContext.current),
            title = "Title",
            moreMenu = buildList {
                add(Pair("", null))
            }.toTypedArray()
        ) {
            repeat(16) {
                WideButton({})
            }
        }
    }
}