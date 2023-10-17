package io.kmptemplate.buildlogic.desktop

import io.kmptemplate.buildlogic.ConventionPlugin
import io.kmptemplate.buildlogic.commonExtensions
import io.kmptemplate.buildlogic.commonTasks
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.compose.ComposePlugin

internal class AppDesktopApplicationPlugin : ConventionPlugin {
    override fun Project.configure() {
        apply(plugin = "org.jetbrains.kotlin.jvm")
        apply(plugin = "org.jetbrains.compose")
        apply(plugin = "app.sonar.kotlin")
        apply(plugin = "app.sentry")

        with(extensions) {
            commonExtensions()
        }

        tasks.commonTasks()

        dependencies {
            val compose =
                (this as ExtensionAware).extensions.getByName("compose") as ComposePlugin.Dependencies
            "implementation"(compose.desktop.currentOs)
            // Include the Test API
            "testImplementation"(compose.desktop.uiTestJUnit4)
        }
    }
}
