package com.crislozano.mynewsapp.presentation

import android.app.Application
import com.crislozano.mynewsapp.data.di.dataModule
import com.crislozano.mynewsapp.domain.di.domainModule
import com.crislozano.mynewsapp.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyNewsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyNewsApplication)
            modules(listOf(dataModule, domainModule, presentationModule))
        }
    }
}