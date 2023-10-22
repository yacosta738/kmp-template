package io.kmptemplate.domain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
