package io.kmptemplate.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.kmptemplate.ui.base.design.ApplicationTheme
import io.kmptemplate.ui.main.view.GreetingView

@Preview
@Composable
fun DefaultPreview() {
    ApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
