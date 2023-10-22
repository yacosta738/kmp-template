package io.kmptemplate.buildlogic.multiplatform

import javax.inject.Inject
import org.gradle.api.Project

open class AppMultiplatformDesktopExtension @Inject constructor(
    project: Project
) : AppMultiplatformCoreExtension(project)
