package io.kmptemplate.buildlogic.multiplatform

import javax.inject.Inject
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

open class AppMultiplatformDesktopExtension @Inject constructor(
    project: Project
) : AppMultiplatformCoreExtension(project)
