package io.kmptemplate.buildlogic.analysis

import io.kmptemplate.buildlogic.ConventionPlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.sonarqube.gradle.SonarExtension

internal class AppSonarMobilePlugin : ConventionPlugin {
    override fun Project.configure() {
        extensions.configure<SonarExtension> {
            val buildDir = layout.buildDirectory.asFile.get()

            properties {
                property(
                    "sonar.android.lint.report",
                    "$buildDir/reports/lint-results-debug.xml",
                )
                property(
                    "sonar.coverage.jacoco.xmlReportPaths",
                    "$buildDir/reports/kover/report.xml",
                )
                property("sonar.java.binaries", "$buildDir/tmp/kotlin-classes/debug")
                property(
                    "sonar.junit.reportPaths",
                    "$buildDir/test-results/testDebugUnitTest",
                )
                property(
                    "sonar.sources",
                    listOf(
                        "$projectDir/src/commonMain/kotlin",
                        "$projectDir/src/androidMain/kotlin",
                        "$projectDir/src/iosMain/kotlin",
                    ),
                )
                property(
                    "sonar.tests",
                    listOf(
                        "$projectDir/src/commonTest/kotlin",
                        "$projectDir/src/androidUnitTest/kotlin",
                        "$projectDir/src/iosTest/kotlin",
                    ),
                )
            }
        }
    }
}
