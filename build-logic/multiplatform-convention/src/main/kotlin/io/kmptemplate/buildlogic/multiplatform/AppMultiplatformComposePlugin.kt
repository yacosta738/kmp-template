package io.kmptemplate.buildlogic.multiplatform

import io.kmptemplate.buildlogic.ConventionPlugin
import io.kmptemplate.buildlogic.catalogBundle
import io.kmptemplate.buildlogic.catalogLib
import java.io.File
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.*
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal class AppMultiplatformComposePlugin : ConventionPlugin {
    override fun Project.configure() {
        apply(plugin = "app.multiplatform.mobile")
        apply(plugin = "org.jetbrains.compose")

        with(extensions) {
            configure<KotlinMultiplatformExtension> { configureSourceSets() }
            configure<ComposeExtension> { configureComposeMultiplatform(project) }
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
                    implementation(catalogBundle("ui-common"))
                }
            }
            val androidMain by getting {
                dependsOn(commonMain)
                dependencies {
                    implementation(compose.preview)
                    implementation(compose.uiTooling)
                    implementation(catalogBundle("ui-android"))
                }
            }
            val iosMain by getting {
                dependsOn(commonMain)
                dependencies {
                    implementation(catalogBundle("ui-ios"))
                }
            }
            val iosSimulatorArm64Main by getting { dependsOn(iosMain) }

            val commonTest by getting {
                dependencies {
                    implementation(catalogBundle("ui-common-test"))
                }
            }
            val androidUnitTest by getting {
                dependsOn(commonTest)
                dependencies {
                    implementation(catalogBundle("ui-android-test"))
                }
            }
            val iosTest by getting {
                dependsOn(commonTest)
                dependencies {
                    implementation(catalogBundle("ui-ios-test"))
                }
            }
            val iosSimulatorArm64Test by getting { dependsOn(iosTest) }
        }
    }

    private fun ComposeExtension.configureComposeMultiplatform(project: Project) {
        kotlinCompilerPlugin.set(project.catalogLib("compose-compiler").get().toString())

        kotlinCompilerPluginArgs.set(
            buildList {
                if (project.composePluginEnabled("app.enableComposeCompilerMetrics")) {
                    add("metricsDestination=${project.composePluginDir("compose-metrics")}")
                }

                if (project.composePluginEnabled("app.enableComposeCompilerReports")) {
                    add("reportsDestination=${project.composePluginDir("compose-reports")}")
                }
            },
        )
    }

    private fun Project.kspDependencies() {
        dependencies {
            addProvider("kspCommonMainMetadata", catalogBundle("ui-common-ksp"))
            addProvider("kspAndroid", catalogBundle("ui-android-ksp"))
            addProvider("kspIosArm64", catalogBundle(UI_IOS_KSP))
            addProvider("kspIosSimulatorArm64", catalogBundle(UI_IOS_KSP))
            addProvider("kspIosX64", catalogBundle(UI_IOS_KSP))
        }
    }

    private fun Project.composePluginEnabled(property: String) =
        providers.gradleProperty(property).orNull == "true"

    private fun Project.composePluginDir(directory: String) =
        File(layout.buildDirectory.asFile.get(), directory).absolutePath

    private companion object {
        const val UI_IOS_KSP = "ui-ios-ksp"
    }
}
