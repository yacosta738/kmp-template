plugins {
    `kotlin-dsl`
}

group = "io.kmptemplate.buildlogic.desktop"
version = extra["app.plugins.version"].toString()

dependencies {
    implementation(libs.gradle.compose)
    implementation(libs.gradle.kotlin)
    implementation(project(":common"))
}

gradlePlugin {
    plugins {
        register("desktop-application") {
            id = "app.desktop.application"
            implementationClass = "io.kmptemplate.buildlogic.desktop.AppDesktopApplicationPlugin"
        }
    }
}
