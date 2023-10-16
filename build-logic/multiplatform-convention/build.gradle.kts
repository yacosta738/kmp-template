plugins {
    `kotlin-dsl`
}

group = "io.kmptemplate.buildlogic.multiplatform"
version = extra["app.plugins.version"].toString()

dependencies {
    implementation(libs.gradle.android)
    implementation(libs.gradle.buildconfig)
    implementation(libs.gradle.compose)
    implementation(libs.gradle.kotest)
    implementation(libs.gradle.kotlin)
    implementation(libs.gradle.ksp)
    implementation(libs.gradle.mockmp)
    implementation(libs.yamlbeans)
    implementation(project(":common"))
}

gradlePlugin {
    plugins {
        register("multiplatform-core") {
            id = "app.multiplatform.core"
            implementationClass = "io.kmptemplate.buildlogic.multiplatform.AppMultiplatformCorePlugin"
        }
        register("multiplatform-mobile") {
            id = "app.multiplatform.mobile"
            implementationClass = "io.kmptemplate.buildlogic.multiplatform.AppMultiplatformMobilePlugin"
        }
        register("multiplatform-compose") {
            id = "app.multiplatform.compose"
            implementationClass = "io.kmptemplate.buildlogic.multiplatform.AppMultiplatformComposePlugin"
        }
        register("multiplatform-desktop") {
            id = "app.multiplatform.desktop"
            implementationClass = "io.kmptemplate.buildlogic.multiplatform.AppMultiplatformDesktopPlugin"
        }
        register("multiplatform-tests") {
            id = "app.multiplatform.tests"
            implementationClass = "io.kmptemplate.buildlogic.multiplatform.AppMultiplatformTestsPlugin"
        }
    }
}
