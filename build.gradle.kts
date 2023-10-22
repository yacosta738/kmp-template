plugins {
    idea
    id("app.common")
    id("app.dependency-versions")
    id("app.detekt")
    id("app.owasp.dependency.check")
    id("app.kover")
    id("app.sonar")
    id("app.documentation.consumer")
    alias(libs.plugins.compose) apply false
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

// // this task generates all tasks for sub-projects itself, therefor it just needs
// // to be applied on the root project, conventions are not working :-(
tasks.dokkaHtmlMultiModule.configure {
    outputDirectory.set(layout.buildDirectory.dir("dokka"))
}

dependencies {
    // implementation(project(":app"))
    asciidoc(project(":documentation"))
}
