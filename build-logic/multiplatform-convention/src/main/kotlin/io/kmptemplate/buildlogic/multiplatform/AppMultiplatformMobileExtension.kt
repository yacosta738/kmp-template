package io.kmptemplate.buildlogic.multiplatform

import javax.inject.Inject
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

open class AppMultiplatformMobileExtension @Inject constructor(
    project: Project
) : AppMultiplatformCoreExtension(project) {
    fun androidMainDependencies(dependencies: KotlinDependencyHandler.() -> Unit) {
        configureSourceSet("androidMain", dependencies)
    }

    fun androidUnitTestDependencies(dependencies: KotlinDependencyHandler.() -> Unit) {
        configureSourceSet("androidUnitTest", dependencies)
    }

    fun iosMainDependencies(dependencies: KotlinDependencyHandler.() -> Unit) {
        configureSourceSet("iosMain", dependencies)
    }

    fun iosTestDependencies(dependencies: KotlinDependencyHandler.() -> Unit) {
        configureSourceSet("iosTest", dependencies)
    }

    fun iosSimulatorMainDependencies(dependencies: KotlinDependencyHandler.() -> Unit) {
        configureSourceSet("iosSimulatorArm64Main", dependencies)
    }

    fun iosSimulatorTestDependencies(dependencies: KotlinDependencyHandler.() -> Unit) {
        configureSourceSet("iosSimulatorArm64Test", dependencies)
    }
}
