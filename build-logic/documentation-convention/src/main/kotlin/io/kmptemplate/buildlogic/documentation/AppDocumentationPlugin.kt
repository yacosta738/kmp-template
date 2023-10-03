package io.kmptemplate.buildlogic.documentation

import io.kmptemplate.buildlogic.ConventionPlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

internal class AppDocumentationPlugin : ConventionPlugin {
    override fun Project.configure() {
        apply(plugin = "org.jetbrains.dokka")
    }
}
