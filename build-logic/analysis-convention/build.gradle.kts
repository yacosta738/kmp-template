plugins {
    `kotlin-dsl`
}

group = "io.kmptemplate.buildlogic.analysis"
version = extra["app.plugins.version"].toString()

dependencies {
    implementation(libs.gradle.detekt)
    implementation(libs.gradle.sonarqube)
    implementation(libs.gradle.owasp.depcheck)
    implementation(project(":common"))
}

gradlePlugin {
    plugins {
        register("detekt") {
            id = "app.detekt"
            implementationClass = "io.kmptemplate.buildlogic.analysis.AppDetektPlugin"
        }
        register("sonar") {
            id = "app.sonar"
            implementationClass = "io.kmptemplate.buildlogic.analysis.AppSonarPlugin"
        }
        register("sonar-mobile") {
            id = "app.sonar.mobile"
            implementationClass = "io.kmptemplate.buildlogic.analysis.AppSonarMobilePlugin"
        }
        register("sonar-kotlin") {
            id = "app.sonar.kotlin"
            implementationClass = "io.kmptemplate.buildlogic.analysis.AppSonarKotlinPlugin"
        }
        register("owasp-dependency-check") {
            id = "app.owasp.dependency.check"
            implementationClass = "io.kmptemplate.buildlogic.analysis.AppOwaspPlugin"
        }
    }
}
