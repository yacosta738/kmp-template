import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

val kotlinVersion: String = libs.findVersion("kotlin").get().requiredVersion

plugins {
    /** Support convention plugins written in Kotlin. Convention plugins are build scripts in 'src/main'
     * that automatically become available as plugins in the main build.
     */
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
    /** build-logic in combination with this plugin ensures that the version set here
    will be set to the same for all other Kotlin dependencies / plugins in the project. */
    add("implementation", libs.findLibrary("kotlin-gradle").get())
    /** https://kotlinlang.org/docs/all-open-plugin.html
    contains also https://kotlinlang.org/docs/all-open-plugin.html#spring-support
    The all-open compiler plugin adapts Kotlin to the requirements of those frameworks and makes classes annotated
    with a specific annotation and their members open without the explicit open keyword. */
    add("implementation", libs.findLibrary("kotlin-allopen").get())
    /** https://kotlinlang.org/docs/no-arg-plugin.html
    contains also https://kotlinlang.org/docs/no-arg-plugin.html#jpa-support
    The no-arg compiler plugin generates an additional zero-argument constructor for classes
    with a specific annotation. */
    add("implementation", libs.findLibrary("kotlin-noarg").get())
    /** https://github.com/Kotlin/dokka
    Dokka is a documentation engine for Kotlin like JavaDoc for Java */
    add("implementation", libs.findLibrary("dokka-gradle").get())
    /** https://detekt.dev/docs/gettingstarted/gradle/
    A static code analyzer for Kotlin */
    add("implementation", libs.findLibrary("detekt-gradle").get())

    /**
     * https://owasp.org/www-project-dependency-check/
     * OWASP dependency-check is a software composition analysis utility that detects
     * publicly disclosed vulnerabilities
     */
    add("implementation", libs.findLibrary("owasp-depcheck").get())
    /**
     * https://asciidoctor.org/docs/asciidoctor-gradle-plugin/
     * The Asciidoctor Gradle Plugin is a Gradle plugin that converts Asciidoc documents
     */
    add("implementation", libs.findLibrary("asciidoctor-gradle").get())
    /**
     * https://docs.sonarsource.com/sonarqube/9.8/analyzing-source-code/scanners/sonarscanner-for-gradle/
     * SonarScanner for Gradle provides an easy way to start SonarQube analysis of a Gradle project.
     */
    add("implementation", libs.findLibrary("sonarqube-gradle-plugin").get())
    /**
     * https://github.com/bmuschko/gradle-docker-plugin
     * The Gradle Docker plugin allows you to build Docker images and create Docker containers
     */
    add("implementation", libs.findLibrary("gradle-docker-plugin").get())
    /**
     * https://github.com/jacoco/jacoco
     * JaCoCo is a free code coverage library for Java
     */
    add("testImplementation", libs.findLibrary("jacoco-core").get())
    /**
     * https://github.com/n0mer/gradle-git-properties
     * Gradle plugin to expose Git properties to your build
     */
    add("implementation", libs.findLibrary("gradle-git-properties").get())
}
