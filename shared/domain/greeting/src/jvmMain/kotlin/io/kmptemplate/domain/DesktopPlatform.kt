package io.kmptemplate.domain

class DesktopPlatform : Platform {
    override val name: String =
        "Desktop Platform ${System.getProperty("os.version")}"
}
actual fun getPlatform(): Platform = DesktopPlatform()
