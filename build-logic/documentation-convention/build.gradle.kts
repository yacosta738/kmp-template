plugins {
    `kotlin-dsl`
}

group = "io.kmptemplate.buildlogic.documentation"
version = extra["app.plugins.version"].toString()

dependencies {
    implementation(libs.gradle.owasp.depcheck)
    implementation(libs.gradle.detekt)
    implementation(libs.gradle.kotlin)
    implementation(libs.gradle.dokka)
    implementation(libs.gradle.asciidoctor)
    implementation(project(":common"))
}

gradlePlugin {
    plugins {
        register("documentation-common") {
            id = "app.documentation.common"
            implementationClass = "io.kmptemplate.buildlogic.documentation.AppDocumentationPlugin"
        }
        register("documentation-producer") {
            id = "app.documentation.producer"
            implementationClass = "io.kmptemplate.buildlogic.documentation.AppDocumentationProducerPlugin"
        }
        register("documentation-consumer") {
            id = "app.documentation.consumer"
            implementationClass = "io.kmptemplate.buildlogic.documentation.AppDocumentationConsumerPlugin"
        }
    }
}
