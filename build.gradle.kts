plugins {
    id("org.jetbrains.dokka")

    // due to late-binding not working, aggregation should define tasks doc-consumer
    id("io.kmptemplate.aggregation-conventions")

    id("io.kmptemplate.verification.jacoco-consumer-conventions")
    id("io.kmptemplate.verification.test-consumer-conventions")
    id("io.kmptemplate.documentation.documentation-consumer-conventions")

    id("io.kmptemplate.verification.sonarqube-conventions")
}

allprojects {
    group = "io.kmptemplate.gradle"
}

repositories {
    mavenCentral()
}

// this task generates all tasks for sub-projects itself, therefor it just needs
// to be applied on the root project, conventions are not working :-(
tasks.dokkaHtmlMultiModule.configure {
    outputDirectory.set(buildDir.resolve("dokka"))
}

dependencies {
    implementation(project(":app"))
    asciidoc(project(":documentation"))
}
