package io.kmptemplate.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.kmptemplate.domain.Greeting
import io.kmptemplate.shared.ui.main.R
import io.kmptemplate.ui.base.design.ApplicationTheme
import io.kmptemplate.ui.main.sentry.initApp
import io.kmptemplate.ui.main.di.applicationModule
import io.kmptemplate.ui.main.di.platformModule
import org.koin.compose.KoinApplication
import org.koin.compose.LocalKoinApplication

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KmpTemplateApp() {
    var text by remember { mutableStateOf("Hello, World!") }
    var presses by remember { mutableIntStateOf(0) }
    KmpTemplateDI {
        initApp(LocalKoinApplication.current.get())
        ApplicationTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("KMP Template")
                            }
                        )
                    },
                    bottomBar = {
                        BottomAppBar(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.primary,
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                text = "Bottom app bar",
                            )
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = { presses++ }) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                      Row(
                          modifier = Modifier
                              .fillMaxWidth()
                              .padding(16.dp)
                              .align(Alignment.CenterHorizontally)
                              .testTag("row"),
                          horizontalArrangement = Arrangement.spacedBy(16.dp),
                      ){
                          Button(
                              onClick = { text = Greeting().greet() },
                              modifier = Modifier.testTag("button")
                          ) {
                              androidx.compose.material.Text(text)
                          }
                      }
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = stringResource(R.string.main_introduction_text, presses).trimIndent(),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun KmpTemplateDI(
    content: @Composable () -> Unit,
) {
    val platformModule = platformModule()

    KoinApplication(
        application = { modules(applicationModule, platformModule) },
        content = content,
    )
}
