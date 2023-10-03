package io.kmptemplate.buildlogic.multiplatform

import com.android.build.gradle.LibraryExtension
import com.github.gmazzo.gradle.plugins.BuildConfigExtension
import io.kmptemplate.buildlogic.ConventionPlugin
import io.kmptemplate.buildlogic.catalogBundle
import io.kmptemplate.buildlogic.commonExtensions
import io.kmptemplate.buildlogic.commonTasks
import io.kmptemplate.buildlogic.configureAndroid
import io.kmptemplate.buildlogic.fullPackageName
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.getting
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal class AppMultiplatformMobilePlugin : ConventionPlugin {
    override fun Project.configure() {
        apply(plugin = "org.jetbrains.kotlin.multiplatform")
        apply(plugin = "org.jetbrains.kotlin.native.cocoapods")
        apply(plugin = "com.android.library")
        apply(plugin = "com.github.gmazzo.buildconfig")
        apply(plugin = "app.sonar.mobile")
        apply(plugin = "com.google.devtools.ksp")
        apply(plugin = "io.kotest.multiplatform")
        apply(plugin = "org.kodein.mock.mockmp")

        with(extensions) {
            create<AppMultiplatformMobileExtension>(APP_MULTIPLATFORM_EXTENSION)

            commonExtensions()
            configure<KotlinMultiplatformExtension> { configureMultiplatform(project) }
            configure<LibraryExtension> { configureAndroid(project.fullPackageName) }
            configure<BuildConfigExtension> { configureBuildConfig(project) }
        }

        tasks.commonTasks()
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    private fun KotlinMultiplatformExtension.configureMultiplatform(project: Project) {
        targetHierarchy.default()

        androidTarget()
        ios()
        iosSimulatorArm64()

        configureCocoapods(project)
        configureSourceSets()
    }

    @Suppress("UNUSED_VARIABLE")
    private fun KotlinMultiplatformExtension.configureSourceSets() {
        configureSourceSets {
            val commonMain by getting {
                kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")

                dependencies {
                    implementation(catalogBundle("mobile-common"))
                }
            }
            val androidMain by getting {
                dependsOn(commonMain)
                dependencies {
                    implementation(catalogBundle("mobile-android"))
                }
            }
            val iosMain by getting {
                dependsOn(commonMain)
                dependencies {
                    implementation(catalogBundle("mobile-ios"))
                }
            }
            val iosSimulatorArm64Main by getting { dependsOn(iosMain) }

            val commonTest by getting {
                dependencies {
                    implementation(catalogBundle("mobile-common-test"))
                }
            }
            val androidUnitTest by getting {
                dependsOn(commonTest)
                dependencies {
                    implementation(catalogBundle("mobile-android-test"))
                }
            }
            val iosTest by getting {
                dependsOn(commonTest)
                dependencies {
                    implementation(catalogBundle("mobile-ios-test"))
                }
            }
            val iosSimulatorArm64Test by getting { dependsOn(iosTest) }
        }
    }

    private fun BuildConfigExtension.configureBuildConfig(project: Project) {
        packageName.set(project.fullPackageName)

        project.appBuildConfig(
            android = { androidConfig ->
                sourceSets.getByName("androidMain") {
                    className.set(BUILD_CONFIG_FILE)
                    androidConfig.forEach { config ->
                        buildConfigField(config.type, config.name, config.value)
                    }
                }
            },
            ios = { iosConfig ->
                sourceSets.getByName("iosMain") {
                    className.set(BUILD_CONFIG_FILE)
                    iosConfig.forEach { config ->
                        buildConfigField(config.type, config.name, config.value)
                    }
                }
            },
        )
    }

    private companion object {
        const val BUILD_CONFIG_FILE = "AppBuildConfig"
    }
}
