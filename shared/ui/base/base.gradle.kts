plugins {
    id("app.multiplatform.compose")
}

appMultiplatform{
    commonMainDependencies{
        implementation(projects.shared.common.core)
        implementation(libs.sentry.multiplatform)
    }

    androidMainDependencies {
        implementation(libs.sentry.compose)
    }

    commonTestDependencies {
        implementation(projects.shared.common.tests)
    }
}
