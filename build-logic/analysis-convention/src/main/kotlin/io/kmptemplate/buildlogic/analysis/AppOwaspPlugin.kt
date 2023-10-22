package io.kmptemplate.buildlogic.analysis

import io.kmptemplate.buildlogic.ConventionPlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.owasp.dependencycheck.gradle.extension.DependencyCheckExtension
import org.owasp.dependencycheck.reporting.ReportGenerator

private const val FAIL_BUILS_ON_CVSS: Float = 9F

internal class AppOwaspPlugin : ConventionPlugin {
    override fun Project.configure() {
        apply(plugin = "org.owasp.dependencycheck")

        with(extensions) {
            configure<DependencyCheckExtension> {
                FAIL_BUILS_ON_CVSS.also { failBuildOnCVSS = it }
                formats = listOf(
                    ReportGenerator.Format.HTML.toString(),
                    ReportGenerator.Format.JUNIT.toString(),
                    ReportGenerator.Format.XML.toString(),
                    ReportGenerator.Format.SARIF.toString(),
                )
                suppressionFile = "${rootProject.rootDir}/config/owasp/owasp-supression.xml"

                // remove plugin dependencies, for configs see
                // https://docs.gradle.org/current/userguide/java_plugin.html#sec:java_plugin_and_dependency_management
                val validConfigurations = listOf("compileClasspath", "runtimeClasspath", "default")
                scanConfigurations = configurations.names
                    .filter { validConfigurations.contains(it) }
                    .toList()
                outputDirectory = layout.buildDirectory.dir("reports/owasp").get().asFile.absolutePath
            }
        }
    }
}
