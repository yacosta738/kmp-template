import org.gradle.kotlin.dsl.support.listFilesOrdered

rootProject.name = "kmp-template"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

pluginManagement {
    includeBuild("build-logic")
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

plugins {
    id("com.gradle.enterprise") version "3.15"
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

// Keep in sync with build-logic/settings.gradle.kts
buildCache {
    local {
        directory = rootDir.resolve(".gradle/build-cache")
    }
}
fun isGradleKtsProjectDirectory(directory: File) =
    directory.isDirectory &&
        (
            directory.resolve("tests.gradle.kts").exists() ||
                directory.resolve("${directory.name}.gradle.kts").exists()
            ) &&
        directory.name !in excludedProjects

fun includeGradleProjectsRecursively(directoryPath: String) {
    val baseDirectory = rootDir.resolve(directoryPath)
    baseDirectory.walkTopDown()
        .maxDepth(1)
        .filter { it.isDirectory }
        .forEach { subDir ->
            includeProjectsInDirectory(subDir.path)
        }
}

fun includeProject(dir: File) {
    println("\uD83D\uDCE6 Loading submodule: ${dir.name}")
    val projectName = calculateProjectName(dir)
    include(projectName)
    val prj = project(":$projectName")
    prj.projectDir = dir
    prj.buildFileName = "${dir.name}.gradle.kts"
    require(prj.projectDir.isDirectory) { "Project '${prj.path} must have a ${prj.projectDir} directory" }
    require(prj.buildFile.isFile) { "Project '${prj.path} must have a ${prj.buildFile} build script" }
}

fun calculateProjectName(dir: File): String {
    // must replace / with : for gradle project names (e.g. shared/common/tests)
    val projectName = dir.relativeTo(rootDir).path.replace("/", ":")
    println("\uD83D\uDFE2 Project name: $projectName")
    return if (projectName.startsWith(":")) projectName.substring(1) else projectName
}

fun includeProjectsInDirectory(directoryPath: String) {
    val baseDirectory = rootDir.resolve(directoryPath)
    println("\uD83D\uDFE3 Loading projects from \uD83D\uDCC2  $baseDirectory")
    baseDirectory.listFilesOrdered()
        .filter { isGradleKtsProjectDirectory(it) }
        .forEach { projectDirectory ->
            includeProject(projectDirectory)
        }
}

val excludedProjects = listOf("iosApp")
val projects = listOf("apps", "shared")
projects.forEach { includeGradleProjectsRecursively(it) }

includeProject(file("documentation"))

if (!System.getenv("CI").isNullOrEmpty() && !System.getenv("BUILD_SCAN_TOS_ACCEPTED")
        .isNullOrEmpty()
) {
    gradleEnterprise {
        buildScan {
            termsOfServiceUrl = "https://gradle.com/terms-of-service"
            termsOfServiceAgree = "yes"
            tag("CI")
        }
    }
}

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
