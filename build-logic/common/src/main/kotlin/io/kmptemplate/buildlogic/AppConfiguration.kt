package io.kmptemplate.buildlogic


import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget as KtJvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion as KtVersion

object AppConfiguration {
    const val appName = "KMP Template"
    const val compileSdk = 34
    const val buildTools = "34.0.0"
    const val packageName = "io.kmptemplate"
    const val minSdk = 21
    const val targetSdk = 34
    const val versionName = "0.0.1"
    const val versionCode = 1

    val useJavaVersion = JavaVersion.VERSION_17
    val jvmTarget = KtJvmTarget.fromTarget(useJavaVersion.toString())
    val jvmTargetStr = jvmTarget.target
    val kotlinVersion = KtVersion.KOTLIN_1_9
}
