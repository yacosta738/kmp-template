package io.kmptemplate.buildlogic.gradle

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.kmptemplate.buildlogic.ConventionPlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.withType

internal class AppDependencyVersionsPlugin : ConventionPlugin {
    private val stableKeywords = arrayOf("RELEASE", "FINAL", "GA")
    private val releaseRegex = "^[\\d,.v-]+(-r)?$".toRegex(RegexOption.IGNORE_CASE)
    private val rcRegex = regex("rc")
    private val betaRegex = regex("beta")
    private val alphaRegex = regex("alpha")
    private val devRegex = regex("dev")

    private fun regex(keyword: String) = "^[\\d,.v-]+(-$keyword\\d*)$".toRegex(RegexOption.IGNORE_CASE)

    private enum class ReleaseType {
        SNAPSHOT,
        DEV,
        ALPHA,
        BETA,
        RC,
        RELEASE;

        fun isLessStableThan(other: ReleaseType): Boolean = ordinal < other.ordinal
    }

    private fun checkDependencyVersion(version: String) =
        if (stableKeywords.any { version.uppercase().contains(it) }) {
            ReleaseType.RELEASE
        } else {
            when {
                releaseRegex.matches(version) -> ReleaseType.RELEASE
                rcRegex.matches(version) -> ReleaseType.RC
                betaRegex.matches(version) -> ReleaseType.BETA
                alphaRegex.matches(version) -> ReleaseType.ALPHA
                devRegex.matches(version) -> ReleaseType.DEV
                else -> ReleaseType.SNAPSHOT
            }
        }

    override fun Project.configure() {
        apply(plugin = "com.github.ben-manes.versions")

        tasks.withType<DependencyUpdatesTask>().configureEach {
            rejectVersionIf {
                @Suppress("UseIfInsteadOfWhen")
                when (val current = checkDependencyVersion(currentVersion)) {
                    ReleaseType.SNAPSHOT -> true // We are using a SNAPSHOT for a reason
                    else -> checkDependencyVersion(candidate.version).isLessStableThan(current)
                }
            }
        }
    }
}
