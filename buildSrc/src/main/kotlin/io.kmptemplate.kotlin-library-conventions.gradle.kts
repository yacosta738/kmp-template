plugins {
    // Apply the common convention plugin for shared build configuration between library and application projects.
    id("io.kmptemplate.kotlin-common-conventions")
    id("io.kmptemplate.publishing.maven-publish-conventions")

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}
