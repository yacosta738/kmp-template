package io.kmptemplate.ui.main.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GreetingView(text: String, modifier: Modifier = Modifier) {
    Text(text = text, modifier = modifier)
}
