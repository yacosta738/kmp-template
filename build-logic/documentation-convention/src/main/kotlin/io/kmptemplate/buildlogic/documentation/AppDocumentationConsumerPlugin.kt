package io.kmptemplate.buildlogic.documentation

import io.gitlab.arturbosch.detekt.Detekt
import io.kmptemplate.buildlogic.ConventionPlugin
import org.asciidoctor.gradle.jvm.AsciidoctorTask
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.attributes.Category
import org.gradle.api.attributes.DocsType
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.*
import org.jetbrains.dokka.gradle.DokkaMultiModuleTask
import org.owasp.dependencycheck.gradle.extension.DependencyCheckExtension
import org.owasp.dependencycheck.gradle.tasks.Aggregate

internal class AppDocumentationConsumerPlugin : ConventionPlugin {
    override fun Project.configure() {

        val asciidoc by configurations.creating {
            isCanBeResolved = true
            isVisible = true
            isCanBeConsumed = false
            attributes {
                attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.DOCUMENTATION))
                attribute(DocsType.DOCS_TYPE_ATTRIBUTE, objects.named("asciidoc-html-folder"))
            }
        }

        val dokkaHtmlMultiModuleTask = tasks.named<DokkaMultiModuleTask>("dokkaHtmlMultiModule")
        val testReportTask = tasks.named("unitTests")
//        val jacocoReportTask = tasks.named("aggregateJacocoTestReport")
//        val detektReportTask = tasks.named<Detekt>("aggregateDetekt")
//        tasks.named<Aggregate>("dependencyCheckAggregate")

        registerAggregateReports(dokkaHtmlMultiModuleTask, testReportTask, /*jacocoReportTask, detektReportTask*/)

        registerAggregateDocumentation(asciidoc)
    }

    private fun Project.registerAggregateReports(
        dokkaHtmlMultiModuleTask: TaskProvider<DokkaMultiModuleTask>,
        testReportTask: TaskProvider<Task>,
//        jacocoReportTask: TaskProvider<Task>,
//        detektReportTask: TaskProvider<Detekt>
    ) {
        tasks.register("aggregateReports") {
            dependsOn(dokkaHtmlMultiModuleTask)
            dependsOn(testReportTask)
//            dependsOn(jacocoReportTask)
//            dependsOn(detektReportTask)

            doLast {
                val targetDir = layout.buildDirectory.dir("documentation").get().asFile.toPath()
                copy {
                    into(targetDir.resolve("dokka"))
                    from(dokkaHtmlMultiModuleTask.map { task -> task.outputDirectory })
                }

                copy {
                    into(targetDir.resolve("tests"))
                    from(testReportTask.map { task -> task.outputs })
                }

//                copy {
//                    into(targetDir.resolve("jacoco"))
//                    from(jacocoReportTask.map { task -> task.outputs })
//                }

//                copy {
//                    into(targetDir.resolve("detekt"))
//                    from(detektReportTask.map { task -> task.outputs })
//                }
            }
        }
    }

    private fun Project.registerAggregateDocumentation(asciidoc: Configuration) {
        tasks.register("aggregateDocumentation") {
            asciidoc.dependencies
                .filterIsInstance<ProjectDependency>()
                .map { it.dependencyProject.tasks.withType<AsciidoctorTask>() }
                .forEach { dependsOn(it) }

            doLast {
                val targetDir = layout.buildDirectory.dir("documentation").get().asFile.toPath()

                copy {
                    into(targetDir.resolve("owasp"))
                    project.extensions.findByType<DependencyCheckExtension>()?.let {
                        from(it.outputDirectory)
                    }
                }

                copy {
                    into(targetDir.resolve("pages"))
                    from(asciidoc.incoming.artifactView { lenient(true) }.files)
                }

                copy {
                    into(targetDir)
                    from("src/documentation")
                }
            }
        }
    }
}
