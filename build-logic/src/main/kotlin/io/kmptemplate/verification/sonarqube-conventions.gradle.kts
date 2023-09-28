package io.kmptemplate.verification

import org.sonarqube.gradle.SonarTask

plugins {
    `java-library`
    id("org.sonarqube")
}

val githubOrg: String by project
val githubProjectUrl = "https://github.com/${githubOrg}/${rootProject.name}"

sonar {
    properties {
        // See https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner+for+Gradle#AnalyzingwithSonarQubeScannerforGradle-Configureanalysisproperties
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.projectName", rootProject.name)
        property("sonar.projectKey", System.getenv()["SONAR_PROJECT_KEY"] ?: rootProject.name)
        property("sonar.organization", System.getenv()["SONAR_ORGANIZATION"] ?: githubOrg)
        property("sonar.projectVersion", rootProject.version.toString())
        property("sonar.host.url", System.getenv()["SONAR_HOST_URL"] ?: "https://sonarcloud.io")
        property("sonar.login", System.getenv()["SONAR_TOKEN"] ?: "")
        property("sonar.scm.provider", "git")
        property("sonar.links.homepage", githubProjectUrl)
        property("sonar.links.ci", "$githubProjectUrl/actions")
        property("sonar.links.scm", githubProjectUrl)
        property("sonar.links.issue", "$githubProjectUrl/issues")
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            layout.buildDirectory.file("reports/jacoco/aggregateJacocoTestReport/aggregateJacocoTestReport.xml")
                .get().asFile.absolutePath
        )
    }
}

tasks.withType<SonarTask>().configureEach {
    dependsOn(project.tasks.named("aggregateJacocoTestReport"))
}
