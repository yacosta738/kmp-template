plugins {
    id("app.multiplatform.compose")
}

appMultiplatform {
    commonMainDependencies {
        implementation(projects.shared.common.core)
    }
    commonTestDependencies {
        implementation(projects.shared.common.tests)
    }
}
