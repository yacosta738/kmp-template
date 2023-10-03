plugins {
    `kotlin-dsl`
}

group = "io.kmptemplate.buildlogic.gradle"
version = extra["app.plugins.version"].toString()

dependencies {
    implementation(libs.gradle.complete.kotlin)
    implementation(libs.gradle.kotlin)
    implementation(libs.gradle.kover)
    implementation(libs.gradle.sentry)
    implementation(libs.gradle.updates)
    implementation(project(":common"))
}

gradlePlugin {
    plugins {
        register("common") {
            id = "app.common"
            implementationClass = "io.kmptemplate.buildlogic.gradle.AppCommonPlugin"
        }
        register("dependency-versions") {
            id = "app.dependency-versions"
            implementationClass = "io.kmptemplate.buildlogic.gradle.AppDependencyVersionsPlugin"
        }
        register("kover") {
            id = "app.kover"
            implementationClass = "io.kmptemplate.buildlogic.gradle.AppKoverPlugin"
        }
        register("sentry") {
            id = "app.sentry"
            implementationClass = "io.kmptemplate.buildlogic.gradle.AppSentryPlugin"
        }
    }
}
