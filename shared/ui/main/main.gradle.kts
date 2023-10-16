plugins {
    id("app.multiplatform.compose")
}

appMultiplatform {
    commonMainDependencies {
        implementation(libs.compose.material3.windowsize)
        implementation(libs.sentry.multiplatform)

        implementation(projects.shared.domain.greeting)

        implementation(projects.shared.ui.base)
    }
    commonTestDependencies {
        implementation(projects.shared.common.tests)
    }

    androidMainDependencies {
        implementation(libs.sentry.compose)
    }
}
