plugins {
    `kotlin-dsl`
}

group = "io.kmptemplate.buildlogic.android"
version = extra["app.plugins.version"].toString()

dependencies {
    implementation(libs.gradle.android)
    implementation(libs.gradle.compose)
    implementation(libs.gradle.kotlin)
    implementation(project(":common"))
}

gradlePlugin {
    plugins {
        register("android-application") {
            id = "app.android.application"
            implementationClass = "io.kmptemplate.buildlogic.android.AppAndroidApplicationPlugin"
        }
    }
}
