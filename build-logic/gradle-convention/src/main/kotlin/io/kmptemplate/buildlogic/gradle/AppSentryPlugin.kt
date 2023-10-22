package io.kmptemplate.buildlogic.gradle

import io.kmptemplate.buildlogic.ConventionPlugin
import io.sentry.android.gradle.extensions.SentryPluginExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

internal class AppSentryPlugin : ConventionPlugin {
    override fun Project.configure() {
        apply(plugin = "io.sentry.android.gradle")

//        val props = Properties().also { p ->
//            runCatching {
//                FileInputStream(rootProject.file("local.properties")).use { f ->
//                    p.load(f)
//                }
//            }
//        }

//        val orgSentry = props.getValue("orgSentry", "ORG_SENTRY")
//        val projectNameSentry = props.getValue("projectNameSentry", "PROJECT_NAME")

        extensions.configure<SentryPluginExtension> {
//            org.set(orgSentry)
//            projectName.set(projectNameSentry)

            // this will upload your source code to Sentry to show it as part of the stack traces
            // disable if you don't want to expose your sources
            includeSourceContext.set(true)
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
