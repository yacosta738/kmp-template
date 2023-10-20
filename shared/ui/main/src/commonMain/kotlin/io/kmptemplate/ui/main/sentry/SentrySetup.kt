package io.kmptemplate.ui.main.sentry


import co.touchlab.kermit.*
import io.sentry.kotlin.multiplatform.*
import io.sentry.kotlin.multiplatform.protocol.Breadcrumb

expect val DEBUG: Boolean
expect val SENTRY_DSN: String

private fun initNapier() {
    if (DEBUG) {
        Logger.setLogWriters(platformLogWriter(DefaultFormatter))
    } else {
        Logger.setLogWriters(SentryLogger(Severity.Error))
    }
}

private fun initSentry(context: Context?) {
    val configuration = optionsConfiguration()
    if (context != null) {
        Sentry.init(context, configuration)
    } else {
        Sentry.init(configuration)
    }
}

/** Configure scope applicable to all platforms */
fun configureSentryScope() {
    Sentry.configureScope {
        it.setContext("Custom Context", "Shared Context")
        it.setTag("custom-tag", "from shared code")
        it.addAttachment(
            Attachment(
                "This is a shared text attachment".encodeToByteArray(), "shared.log"
            )
        )
    }
}

/**
 * Initializes Sentry with given options. This is a shared function that can be called from any platform.
 * Make sure to hook this into your native platforms as early as possible
 */
fun initApp(context: Context?) {
    initSentry(context) // move this to common module
    initNapier()
}

private class SentryLogger(private val minSeverity: Severity) : LogWriter() {
    private val Severity.sentryLevel
        get() = when (this) {
            Severity.Verbose -> SentryLevel.DEBUG
            Severity.Debug -> SentryLevel.DEBUG
            Severity.Info -> SentryLevel.INFO
            Severity.Warn -> SentryLevel.WARNING
            Severity.Error -> SentryLevel.ERROR
            Severity.Assert -> SentryLevel.FATAL
        }

    override fun isLoggable(tag: String, severity: Severity) = !DEBUG && severity >= minSeverity

    override fun log(severity: Severity, message: String, tag: String, throwable: Throwable?) {
        if (throwable != null && severity >= minSeverity) {
            Sentry.addBreadcrumb(
                Breadcrumb(
                    level = severity.sentryLevel,
                    message = "$tag: $message",
                ),
            )
            Sentry.captureException(throwable)
        }
    }
}


/** Returns a shared options configuration */
private fun optionsConfiguration(): OptionsConfiguration {
    return {
        it.dsn = SENTRY_DSN
        it.attachStackTrace = true
        it.attachThreads = true
        it.attachScreenshot = true
        it.attachViewHierarchy = true
        it.debug = DEBUG
        it.failedRequestStatusCodes = listOf(HttpStatusCodeRange(400, 599))
        it.failedRequestTargets = listOf("httpbin.org")
        it.beforeBreadcrumb = { breadcrumb ->
            breadcrumb.message = "Add message before every breadcrumb"
            breadcrumb
        }
        it.beforeSend = { event ->
            if (event.environment == "test") {
                null
            } else {
                event
            }
        }
    }
}
