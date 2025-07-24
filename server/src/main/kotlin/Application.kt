package com.moashrafff

import com.moashrafff.data.database.factory.initDb
import com.moashrafff.di.configKoin
import com.moashrafff.domain.service.UserService
import com.moashrafff.plugins.configureHTTP
import com.moashrafff.plugins.configureRouting
import com.moashrafff.plugins.configureSecurity
import com.moashrafff.plugins.configureSerialization
import io.ktor.server.application.*
import org.koin.ktor.ext.get

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configKoin()
    initDb()
    configureSerialization()


    val userService = get<UserService>()

    configureHTTP()
    configureSecurity(userService = userService)
    configureRouting(userService = userService)

}