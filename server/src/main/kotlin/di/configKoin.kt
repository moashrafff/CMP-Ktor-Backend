package com.moashrafff.di

import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configKoin() {
    install(Koin) {
        slf4jLogger()
        modules(repoModule, serviceModule)
    }
}