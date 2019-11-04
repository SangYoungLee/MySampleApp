package com.syapp.componentbasicapp

import android.app.Application
import timber.log.Timber

/**
 * Created By lsy2014 on 2019-11-04
 */
class ComponentTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}