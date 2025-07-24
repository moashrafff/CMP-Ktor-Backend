package org.moashrafff.showcase.di

import org.koin.dsl.KoinAppDeclaration
import org.koin.core.context.startKoin

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(platformModule, sharedModule)
    }
}