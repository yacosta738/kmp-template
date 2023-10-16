plugins {
    id("app.multiplatform.core")
}

appMultiplatform {
    commonMainDependencies { implementation(projects.shared.common.core) }
    commonTestDependencies { implementation(projects.shared.common.tests) }
}
