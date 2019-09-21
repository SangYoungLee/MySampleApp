package com.example.mysampleapp.application

import com.example.mysampleapp.repository.ITaskRepository
import com.example.mysampleapp.repository.ServiceLocator
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created By lsy2014 on 2019-09-16
 */
class TodoApplication : DaggerApplication() {

    val taskRepository: ITaskRepository
        get() = ServiceLocator.provideTaskRepository(this)

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}