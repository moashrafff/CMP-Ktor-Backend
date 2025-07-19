package org.moashrafff.showcase

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform