package org.moashrafff.showcase.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.moashrafff.showcase.data.network.NetworkService
import org.moashrafff.showcase.data.network.UserService
import org.moashrafff.showcase.data.network.httpClient

expect val platformModule : Module

val sharedModule = module {
    httpClient()
    single<UserService> { NetworkService(get()) }
}