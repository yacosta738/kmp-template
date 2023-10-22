package io.kmptemplate.buildlogic.analysis

import io.kmptemplate.buildlogic.AppConfiguration
import io.kmptemplate.buildlogic.ConventionPlugin
import java.lang.System
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.sonarqube.gradle.SonarExtension

internal class AppSonarPlugin : ConventionPlugin {
    private val githubOrg: String = System.getenv()["GITHUB_ORG"] ?: "yacosta738"
    private val githubProjectUrl = "https://github.com/$githubOrg/kmp-template"
    private val codeExclusions = listOf(
        "**/R.*",
        "**/R$*.*",
        "**/BuildConfig.*",
    )
    private val coverageExclusions = listOf(
        // App
        "**/AppApp.kt",
        "**/initializers/**",

        // Common
        "**/common/**",

        // Common Android
        "**Activity.kt",
        "**Fragment.kt",
        "**/base/**",
        "**/navigation/**",

        // Koin
        "**/di/**",

        // Ui
        "**/ui/**/components/**",
        "**/ui/**/view/**",
    )

    override fun Project.configure() {
        apply(plugin = "org.sonarqube")

        extensions.configure<SonarExtension> {
            val buildDir = rootProject.layout.buildDirectory.asFile.get()
            // See https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner+for+Gradle#AnalyzingwithSonarQubeScannerforGradle-Configureanalysisproperties
            properties {
                property("sonar.host.url", System.getenv()["SONAR_HOST_URL"] ?: "https://sonarcloud.io")
                property("sonar.organization", System.getenv()["SONAR_ORGANIZATION"] ?: githubOrg)
                property("sonar.projectKey", System.getenv()["SONAR_PROJECT_KEY"] ?: rootProject.name)
                property("sonar.projectName", System.getenv()["SONAR_PROJECT_NAME"] ?: rootProject.name)
                property("sonar.login", System.getenv()["SONAR_TOKEN"] ?: "")
                property(
                    "sonar.projectVersion",
                    "${AppConfiguration.versionName}_(${AppConfiguration.versionCode})",
                )

                property("sonar.pullrequest.github.repository", "$githubOrg/${rootProject.name}")
                property("sonar.pullrequest.provider", "GitHub")
                property("sonar.links.homepage", githubProjectUrl)
                property("sonar.links.ci", "$githubProjectUrl/actions")
                property("sonar.links.scm", githubProjectUrl)
                property("sonar.links.issue", "$githubProjectUrl/issues")

                property("sonar.coverage.exclusions", coverageExclusions)
                property("sonar.exclusions", codeExclusions)
                property("sonar.java.coveragePlugin", "jacoco")
                property("sonar.kotlin.detekt.reportPaths", "$buildDir/reports/detekt/detekt.xml")
                property("sonar.language", "kotlin")
                property("sonar.log.level", "TRACE")
                property("sonar.qualitygate.wait", true)
                property("sonar.sourceEncoding", "UTF-8")
                property("sonar.tags", "android")
                property("sonar.verbose", true)
                property("sonar.scm.provider", "git")
            }
        }
    }
}
