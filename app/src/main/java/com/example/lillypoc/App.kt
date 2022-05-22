package com.example.lillypoc

import android.app.Application
import androidx.databinding.ktx.BuildConfig
import com.example.lillypoc.common.Preference
import com.example.lillypoc.di.injectFeature
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        /**
         * initialized shared preference
         */
        Preference.initialize(this)

        /**
         * initialized koin modules
         */
        startKoin{
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            injectFeature()
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}