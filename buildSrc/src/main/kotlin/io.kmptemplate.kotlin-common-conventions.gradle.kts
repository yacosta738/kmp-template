import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm")
    id("org.jetbrains.dokka")
    id("io.gitlab.arturbosch.detekt")

    id("io.kmptemplate.testing-conventions")
}

repositories {
    mavenCentral()
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
// Latest Java LTS Version
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.majorVersion
    }
}

detekt {
    buildUponDefaultConfig = false
    ignoreFailures = true
}

dependencies {
    constraints {
        // Define dependency versions as constraints
        add("implementation", libs.findLibrary("commons-text").get())
    }

    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    // Add additonal dependencies useful for development
    add("implementation", libs.findLibrary("kotlin-logging").get())
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit5"))
}
