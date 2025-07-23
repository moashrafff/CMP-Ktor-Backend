package com.moashrafff.di

import com.moashrafff.data.service.UserServiceImpl
import com.moashrafff.domain.service.UserService
import org.koin.dsl.module

val serviceModule = module {
    single<UserService> { UserServiceImpl(get()) }
}