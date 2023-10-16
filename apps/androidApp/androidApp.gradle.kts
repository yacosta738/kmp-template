plugins {
    id("app.android.application")
}

dependencies{
    implementation(projects.shared.common.core)

    implementation(projects.shared.ui.base)
    implementation(projects.shared.ui.main)

    testImplementation(projects.shared.common.tests)
}
