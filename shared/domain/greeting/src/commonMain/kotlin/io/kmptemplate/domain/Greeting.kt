package io.kmptemplate.domain

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "\uD83D\uDFE3 Hello, ${platform.name}!"
    }
}
