import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED

plugins {
    // the following conventions depend on each other, keep them in the following order
    id("io.kmptemplate.verification.test-producer-conventions")
    id("io.kmptemplate.verification.jacoco-producer-conventions")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events = setOf(FAILED)
        exceptionFormat = FULL
    }
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies {
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit5"))
    add("testImplementation", libs.findBundle("junit").get())
    add("testImplementation", libs.findLibrary("mockk").get())
    add("testImplementation", libs.findBundle("kotest").get())
    add("testImplementation", libs.findLibrary("assertk-jvm").get())
}
