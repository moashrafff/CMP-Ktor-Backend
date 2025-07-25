package org.moashrafff.showcase.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.moashrafff.showcase.data.cache.dataStore

actual val platformModule: Module = module {
    single {
        dataStore(get())
    }
}