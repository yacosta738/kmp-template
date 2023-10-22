import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("app.desktop.application")
}
dependencies {

    implementation(projects.shared.common.core)

    implementation(projects.shared.domain.greeting)

    implementation(projects.shared.ui.base)
    implementation(projects.shared.ui.main)

    testImplementation(projects.shared.common.tests)
}

compose.desktop {
    application {
        mainClass = "io.kmptemplate.desktop.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "KotlinMultiplatformComposeDesktopApplication"
            packageVersion = "1.0.0"
        }
    }
}
