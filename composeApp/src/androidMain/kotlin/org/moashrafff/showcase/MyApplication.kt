package org.moashrafff.showcase

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.moashrafff.showcase.di.initKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(config = {
            androidContext(this@MyApplication)
        })
    }
}