package io.kmptemplate.buildlogic.documentation

import io.kmptemplate.buildlogic.ConventionPlugin
import org.asciidoctor.gradle.jvm.AsciidoctorTask
import org.gradle.api.Project
import org.gradle.api.attributes.Category
import org.gradle.api.attributes.DocsType
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.named
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter

internal class AppDocumentationProducerPlugin : ConventionPlugin {
    override fun Project.configure() {
        apply(plugin = "org.jetbrains.dokka")
        apply(plugin = "org.asciidoctor.jvm.convert")
        val revDate =
            System.getenv()["revdate"] ?: now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val revNumber = System.getenv()["revnumber"] ?: "DEV-Version"

        val asciidoctorTask = tasks.named<AsciidoctorTask>("asciidoctor") {
            setSourceDir(file("docs"))
            setOutputDir(layout.buildDirectory.dir("docs").get().asFile)

            resources {
                from("docs/resources") {
                    include("*.png")
                }

                into("./resources")
            }

            attributes(
                mapOf(
                    "source-highlighter" to "rouge",
                    "toc" to "left",
                    "toclevels" to 2,
                    "idprefix" to "",
                    "idseparator" to "-",
                    "revnumber" to revNumber,
                    "revdate" to revDate
                )
            )
        }

        configurations.create("asciidoctorHtmlFolder") {
            isVisible = false
            isCanBeResolved = false
            isCanBeConsumed = true
            attributes {
                attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.DOCUMENTATION))
                attribute(DocsType.DOCS_TYPE_ATTRIBUTE, objects.named("asciidoc-html-folder"))
            }

            outgoing.artifact(asciidoctorTask.map { task ->
                task.outputDir
            })
        }
    }
}
