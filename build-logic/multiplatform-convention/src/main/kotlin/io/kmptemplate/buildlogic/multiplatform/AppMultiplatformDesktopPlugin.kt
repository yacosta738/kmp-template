package io.kmptemplate.buildlogic.multiplatform

import io.kmptemplate.buildlogic.ConventionPlugin
import io.kmptemplate.buildlogic.catalogBundle
import io.kmptemplate.buildlogic.catalogLib
import io.kmptemplate.buildlogic.commonExtensions
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.*
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal class AppMultiplatformDesktopPlugin : ConventionPlugin {
    override fun Project.configure() {
        apply(plugin = "org.jetbrains.kotlin.multiplatform")
        apply(plugin = "org.jetbrains.compose")

        with(extensions) {
            create<AppMultiplatformDesktopExtension>(APP_MULTIPLATFORM_EXTENSION)

            commonExtensions()
            configure<KotlinMultiplatformExtension> { configureDesktopMultiplatform(project) }
        }

        kspDependencies()
    }

    @Suppress("UNUSED_VARIABLE")
    @OptIn(ExperimentalComposeLibrary::class)
    private fun KotlinMultiplatformExtension.configureSourceSets() {
        val compose = (this as ExtensionAware).extensions.getByName("compose") as ComposePlugin.Dependencies

        configureSourceSets {
            val commonMain by getting {
                dependencies {
                    implementation(compose.animation)
                    implementation(compose.components.resources)
                    implementation(compose.foundation)
                    implementation(compose.material)
                    implementation(compose.material3)
                    implementation(compose.materialIconsExtended)
                    implementation(compose.runtime)
                    implementation(compose.ui)
                    // Note, if you develop a library, you should use compose.desktop.common.
                    // compose.desktop.currentOs should be used in launcher-sourceSet
                    // (in a separate module for demo project and in testMain).
                    // With compose.desktop.common you will also lose @Preview functionality
                    implementation(compose.desktop.common)
                    implementation(catalogLib("koin"))
                    implementation(catalogBundle("ui-common"))
                }
            }

            val commonTest by getting {
                dependencies {
                    implementation(catalogBundle("ui-common-test"))
                }
            }
        }
    }

    private fun KotlinMultiplatformExtension.configureDesktopMultiplatform(project: Project) {
        println("⚪ Configuring multiplatform desktop for ${project.name}")
        jvm("desktop")
        configureSourceSets()
    }

    private fun Project.kspDependencies() {
        dependencies {
        }
    }
}
