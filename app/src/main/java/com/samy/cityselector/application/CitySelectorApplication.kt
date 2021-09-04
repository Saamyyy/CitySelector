package com.samy.cityselector.application

import android.app.Application
import com.samy.cityselector.di.dataModule
import com.samy.cityselector.di.domainModule
import com.samy.cityselector.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CitySelectorApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CitySelectorApplication)
            modules(listOf(dataModule, domainModule, viewModelModule))
        }
    }
}
