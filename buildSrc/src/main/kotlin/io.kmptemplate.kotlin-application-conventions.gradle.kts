plugins {
    id("io.kmptemplate.kotlin-common-conventions")
    id("io.kmptemplate.publishing.maven-publish-conventions")

    id("com.bmuschko.docker-java-application")
    application
}

val githubOrg: String by project
val dockerTag = System.getenv()["revnumber"] ?: "latest"

docker {
    javaApplication {
        baseImage.set("eclipse-temurin:17-jre")
        maintainer.set("Yuniel Acosta 'ylaz@gft.com'")
        jvmArgs.set(listOf("-server", "-XX:+UnlockExperimentalVMOptions", "-XX:InitialRAMFraction=2",
            "-XX:MinRAMFraction=2", "-XX:MaxRAMFraction=2", "-XX:+UseG1GC",
            "-XX:MaxGCPauseMillis=100", "-XX:+UseStringDeduplication"))
        ports.set(listOf(8080))
        mainClassName.set(project.application.mainClass)
        images.set(listOf("${project.group}/${rootProject.name}:latest",
            "ghcr.io/${githubOrg}/${rootProject.name}:${dockerTag}"))
    }
}
