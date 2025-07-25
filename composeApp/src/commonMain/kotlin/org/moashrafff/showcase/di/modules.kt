package org.moashrafff.showcase.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.moashrafff.showcase.data.cache.ShowcaseSession
import org.moashrafff.showcase.data.network.NetworkService
import org.moashrafff.showcase.data.network.UserService
import org.moashrafff.showcase.data.network.httpClient
import org.moashrafff.showcase.presentation.features.login.LoginViewModel
import org.moashrafff.showcase.presentation.features.register.RegisterViewModel

expect val platformModule : Module

val sharedModule = module {
    httpClient()
    single<UserService> { NetworkService(get()) }
    viewModel {
        RegisterViewModel(get(), get())
    }
    viewModel {
        LoginViewModel(get(), get())
    }
    single<ShowcaseSession> {
        ShowcaseSession(get())
    }
}