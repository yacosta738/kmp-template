plugins {
    idea
    base
    id("org.jetbrains.dokka")

    // due to late-binding not working, aggregation should define tasks doc-consumer
    id("io.kmptemplate.aggregation-conventions")

    id("io.kmptemplate.verification.jacoco-consumer-conventions")
    id("io.kmptemplate.verification.test-consumer-conventions")
    id("io.kmptemplate.documentation.documentation-consumer-conventions")

    id("io.kmptemplate.verification.sonarqube-conventions")
}

idea {
    module.isDownloadJavadoc = true
    module.isDownloadSources = true
}


allprojects {
    group = properties["group"] as String
}

repositories {
    mavenCentral()
}

// this task generates all tasks for sub-projects itself, therefor it just needs
// to be applied on the root project, conventions are not working :-(
tasks.dokkaHtmlMultiModule.configure {
    outputDirectory.set(layout.buildDirectory.dir("dokka"))
}

dependencies {
    implementation(project(":app"))
    asciidoc(project(":documentation"))
}
