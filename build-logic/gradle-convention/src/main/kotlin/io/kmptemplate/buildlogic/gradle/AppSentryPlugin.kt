package io.kmptemplate.buildlogic.gradle

import io.kmptemplate.buildlogic.ConventionPlugin
import io.sentry.android.gradle.extensions.SentryPluginExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

internal class AppSentryPlugin : ConventionPlugin {
    override fun Project.configure() {
        apply(plugin = "io.sentry.android.gradle")

        extensions.configure<SentryPluginExtension> {
            includeProguardMapping.set(true)
            autoUploadProguardMapping.set(true)
            experimentalGuardsquareSupport.set(false)
            uploadNativeSymbols.set(false)
            includeNativeSources.set(false)
            tracingInstrumentation.enabled.set(false)
            autoInstallation.enabled.set(false)
            ignoredBuildTypes.set(setOf("debug"))
        }
    }
}
