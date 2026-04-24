package com.panov.proxy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.panov.proxy.ui.theme.ProxyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProxyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { screenPadding ->
                    MainScreenExample(screenPadding)
                }
            }
        }
    }
}

@Composable
private fun MainScreenExample(screenPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .padding(screenPadding)
            .fillMaxSize()
    ) {
        Text(text = "Hello, World!", modifier = Modifier.align(alignment = Alignment.Center))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MainScreenExamplePreview() {
    ProxyTheme {
        MainScreenExample(PaddingValues(0.dp, 32.dp, 0.dp, 24.dp))
    }
}