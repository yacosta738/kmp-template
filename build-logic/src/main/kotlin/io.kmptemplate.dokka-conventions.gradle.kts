
plugins{
    id("org.jetbrains.dokka")
}

//// this task generates all tasks for sub-projects itself, therefor it just needs
//// to be applied on the root project, conventions are not working :-(
//tasks.dokkaHtmlMultiModule.configure {
//    outputDirectory.set(layout.buildDirectory.dir("dokka"))
//}
