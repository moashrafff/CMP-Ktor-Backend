package com.moashrafff.di

import com.moashrafff.data.repo.UserRepositoryImpl
import com.moashrafff.domain.repo.UserRepository
import org.koin.dsl.module

val repoModule = module {
    single<UserRepository> {
        UserRepositoryImpl()
    }
}