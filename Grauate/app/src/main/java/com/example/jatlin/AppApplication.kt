package com.example.jatlin

import android.app.Application
import com.example.jatlin.di.appModule
import com.example.jatlin.di.databaseModule
import com.example.jatlin.di.presenterModule
import com.example.jatlin.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // DI 주입
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AppApplication)
            modules(appModule + databaseModule + useCaseModule + presenterModule)
        }
    }
}