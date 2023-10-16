plugins {
    id("app.multiplatform.tests")
}

appMultiplatform {
    commonMainDependencies {
        implementation(libs.arrow)
        implementation(libs.koin)
        implementation(libs.koin.test.get().toString()) { exclude(group = "junit", module = "junit") }
        implementation(libs.mockmp)
    }
}
