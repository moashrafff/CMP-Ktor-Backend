package com.moashrafff

import com.moashrafff.data.database.factory.initDb
import com.moashrafff.plugins.configureHTTP
import com.moashrafff.plugins.configureRouting
import com.moashrafff.plugins.configureSecurity
import com.moashrafff.plugins.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureHTTP()
    configureSerialization()
    configureSecurity()
    configureRouting()
    initDb()
}