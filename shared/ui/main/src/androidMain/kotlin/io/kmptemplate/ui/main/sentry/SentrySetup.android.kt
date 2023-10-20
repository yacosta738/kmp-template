package io.kmptemplate.ui.main.sentry

import io.kmptemplate.shared.ui.main.ApplicationBuildConfig


actual val DEBUG: Boolean
    get() = ApplicationBuildConfig.DEBUG
actual val SENTRY_DSN: String
    get() = ApplicationBuildConfig.SENTRY_DSN
