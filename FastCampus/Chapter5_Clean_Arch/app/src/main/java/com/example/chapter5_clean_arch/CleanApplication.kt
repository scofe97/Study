package com.example.chapter5_clean_arch

import android.app.Application
import com.example.chapter5_clean_arch.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CleanApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        println("CleanApplication 생성")

        // TODO Koin Trigger
        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@CleanApplication)
            modules(appModule)
        }
    }
}