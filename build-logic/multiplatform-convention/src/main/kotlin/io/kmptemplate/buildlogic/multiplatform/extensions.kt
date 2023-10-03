package io.kmptemplate.buildlogic.multiplatform

import io.kmptemplate.buildlogic.AppConfiguration
import java.util.Locale
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.plugin.cocoapods.CocoapodsExtension

internal fun KotlinMultiplatformExtension.configureSourceSets(
    block: NamedDomainObjectContainer<KotlinSourceSet>.() -> Unit
) {
    configureExtension(block)
}

internal inline fun <reified T : Any> KotlinMultiplatformExtension.configureExtension(
    noinline block: T.() -> Unit
) {
    (this as ExtensionAware).extensions.configure(block)
}

internal fun KotlinMultiplatformExtension.configureCocoapods(project: Project) {
    val podName = project.path.split(':')
        .filter { it.isNotEmpty() }
        .reduceRight { acc, s -> "$acc${s.capitalize()}" }

    configureExtension<CocoapodsExtension> {
        name = podName
        homepage = "https://github.com/yacosta738/kmp-template"
        podfile = project.file("${project.rootDir}/iosApp/Podfile")
        summary = "Cocoapod $podName module"
        version = AppConfiguration.versionName
        ios.deploymentTarget = "14.1"

        pod("Sentry", "~> 8.9.5")

        framework {
            baseName = podName
        }
    }
}

private fun String.capitalize() =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
