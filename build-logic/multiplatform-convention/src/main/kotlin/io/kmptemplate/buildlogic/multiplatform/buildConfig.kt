package io.kmptemplate.buildlogic.multiplatform

import com.esotericsoftware.yamlbeans.YamlReader
import org.gradle.api.Project
import java.util.concurrent.atomic.AtomicBoolean

private typealias YamlBuildConfig = Map<String, FlavorBuildConfig>
private typealias FlavorBuildConfig = Map<String, ArrayList<LinkedHashMap<String, String>>>

private var initialized = AtomicBoolean(false)

internal data class BuildConfig(
    val type: String,
    val name: String,
    val value: String,
)

internal fun Project.appBuildConfig(
    android: (List<BuildConfig>) -> Unit,
    ios: (List<BuildConfig>) -> Unit,
) {
    println(" >>> appBuildConfig: $initialized")
    println(" >>> appBuildConfig: $rootDir")
    println(" >>> Project: $projectDir")
    if (!initialized.compareAndSet(false, true)) {
        val flavor = providers.gradleProperty("app.flavor").getOrElse("dev")
        val ymlConfig = rootDir.resolve("config/build_config.yml").readText()
        val yml = YamlReader(ymlConfig).parse<YamlBuildConfig>()

        android(yml["android"].map(flavor))
        ios(yml["ios"].map(flavor))
    }
}

private fun FlavorBuildConfig?.map(flavor: String?) =
    this?.get(flavor)
        .orEmpty()
        .map {
            BuildConfig(
                type = it["type"].orEmpty(),
                name = it["name"].orEmpty(),
                value = it["value"].orEmpty(),
            )
        }

private inline fun <reified T> YamlReader.parse(): T = read(T::class.java)
