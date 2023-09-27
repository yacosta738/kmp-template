package io.kmptemplate.publishing

import org.gradle.api.publish.maven.MavenPublication

val githubOrg: String by project

plugins {
    java
    `maven-publish`
}

java {
    withSourcesJar()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/$githubOrg/${rootProject.name}")
            credentials {
                username = "i-dont-care"
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}
