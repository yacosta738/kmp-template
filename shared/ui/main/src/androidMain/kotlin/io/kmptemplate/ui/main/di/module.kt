package io.kmptemplate.ui.main.di

import android.app.Application
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import org.koin.core.module.Module
import org.koin.dsl.binds
import org.koin.dsl.module

@Composable
internal fun platformModule(): Module {
    val applicationContext = LocalContext.current.applicationContext

    return module {
        single { applicationContext } binds arrayOf(Application::class, Context::class)
    }
}

internal val applicationModule = module {
    includes()
}
