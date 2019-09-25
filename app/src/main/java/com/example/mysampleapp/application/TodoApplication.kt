package com.example.mysampleapp.application

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created By lsy2014 on 2019-09-16
 */
open class TodoApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}