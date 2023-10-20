package io.kmptemplate.ui.base.sentry

import io.kmptemplate.shared.ui.base.ApplicationBuildConfig

actual val DEBUG: Boolean
    get() = ApplicationBuildConfig.DEBUG

actual val SENTRY_DSN: String
    get() = ApplicationBuildConfig.SENTRY_DSN
