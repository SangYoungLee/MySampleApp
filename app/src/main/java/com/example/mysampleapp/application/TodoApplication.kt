package com.example.mysampleapp.application

import android.app.Application
import com.example.mysampleapp.application.di.ServiceLocator
import com.example.mysampleapp.base.repository.ITaskRepository

/**
 * Created By lsy2014 on 2019-09-16
 */
class TodoApplication : Application() {

    val taskRepository: ITaskRepository
        get() = ServiceLocator.provideTaskRepository(this)
}