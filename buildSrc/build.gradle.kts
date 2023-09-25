import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties

val properties = Properties().also { props ->
    project.projectDir.resolveSibling("gradle.properties").bufferedReader().use {
        props.load(it)
    }
}
val kotlinVersion: String = properties.getProperty("kotlinVersion")

plugins {
    // Support convention plugins written in Kotlin. Convention plugins are build scripts in 'src/main' that automatically become available as plugins in the main build.
    `kotlin-dsl`
}

repositories {
    // Use the plugin portal to apply community plugins in convention plugins.
    gradlePluginPortal()
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "17"
}

dependencies {
    implementation(kotlin("gradle-plugin", kotlinVersion))
    implementation(kotlin("bom", kotlinVersion))
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.9.0") {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk8")
    }
    implementation("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:4.3.1.3277")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.1") {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk8")
    }
    implementation("org.owasp:dependency-check-gradle:8.4.0")
    implementation("org.asciidoctor:asciidoctor-gradle-jvm:4.0.0-alpha.1")
    implementation("com.bmuschko:gradle-docker-plugin:9.1.0")
}
