package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.di.appModule
import com.example.rickandmorty.di.dataModule
import com.example.rickandmorty.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger(Level.ERROR)
            modules(appModule, dataModule, domainModule)
        }

    }
}
