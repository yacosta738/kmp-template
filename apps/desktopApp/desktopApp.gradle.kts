import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
//    id("app.android.application")
    id("app.desktop.application")
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)

    implementation(projects.shared.common.core)

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
