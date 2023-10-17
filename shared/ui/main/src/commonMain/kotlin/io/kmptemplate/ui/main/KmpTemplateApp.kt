package io.kmptemplate.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.kmptemplate.domain.Greeting
import io.kmptemplate.ui.base.design.ApplicationTheme
import io.kmptemplate.ui.main.view.GreetingView

@Composable
fun KmpTemplateApp(){
    ApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            GreetingView(Greeting().greet())
        }
    }
}
