package io.kmptemplate.buildlogic.android

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import io.kmptemplate.buildlogic.ConventionPlugin
import io.kmptemplate.buildlogic.AppConfiguration
import io.kmptemplate.buildlogic.catalogBundle
import io.kmptemplate.buildlogic.commonExtensions
import io.kmptemplate.buildlogic.commonTasks
import io.kmptemplate.buildlogic.configureAndroid
import io.kmptemplate.buildlogic.implementation
import java.io.FileInputStream
import java.util.Properties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal class AppAndroidApplicationPlugin : ConventionPlugin {
    override fun Project.configure() {
        apply(plugin = "com.android.application")
        apply(plugin = "org.jetbrains.kotlin.android")
        apply(plugin = "org.jetbrains.compose")
        apply(plugin = "app.sonar.mobile")
        apply(plugin = "app.sentry")

        with(extensions) {
            commonExtensions()
            configure<BaseAppModuleExtension> { configureApp(project) }
        }

        tasks.commonTasks()

        dependencies { implementation(catalogBundle("app")) }
    }

    @Suppress("StringLiteralDuplication")
    private fun BaseAppModuleExtension.configureApp(project: Project) {
        configureAndroid(AppConfiguration.packageName)
        val rootProject = project.rootProject

        defaultConfig.applicationId = AppConfiguration.packageName
        lint.abortOnError = false

        with(packagingOptions.resources.excludes) {
            add("/META-INF/{AL2.0,LGPL2.1}")
            add("DebugProbesKt.bin")
        }

        signingConfigs {
            register("release") {
                val props = Properties().also { p ->
                    runCatching {
                        FileInputStream(rootProject.file("local.properties")).use { f ->
                            p.load(f)
                        }
                    }
                }

                enableV3Signing = true
                enableV4Signing = true

                keyAlias = props.getValue("signingAlias", "SIGNING_ALIAS")
                keyPassword = props.getValue("signingAliasPass", "SIGNING_ALIAS_PASS")
                storeFile = props.getValue("signingFile", "SIGNING_FILE")?.let {
                    rootProject.file(it)
                }
                storePassword = props.getValue("signingFilePass", "SIGNING_FILE_PASS")
            }
        }

        buildTypes {
            debug {
                applicationIdSuffix = ".dev"
                versionNameSuffix = "-dev"

                configure(isDebug = true)
            }

            release {
                configure(isDebug = false)

                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro",
                )

                signingConfig = signingConfigs.getByName("release")
            }

            register("beta") {
                initWith(getByName("release"))
                matchingFallbacks.add("release")

                applicationIdSuffix = ".beta"
                versionNameSuffix = "-beta"
            }
        }
    }

    private fun ApplicationBuildType.configure(isDebug: Boolean) {
        isDebuggable = isDebug
        isDefault = isDebug
        isMinifyEnabled = !isDebug
        isShrinkResources = !isDebug
        enableUnitTestCoverage = isDebug
    }

    private fun Properties.getValue(key: String, env: String) =
        getOrElse(key) { System.getenv(env) } as? String
}
