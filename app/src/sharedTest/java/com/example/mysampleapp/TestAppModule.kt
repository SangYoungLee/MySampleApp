package com.example.mysampleapp

import android.content.Context
import com.example.mysampleapp.activity.ActivityModuleBinds
import com.example.mysampleapp.application.AppModule
import com.example.mysampleapp.application.AppModuleBinds
import com.example.mysampleapp.application.TodoApplication
import com.example.mysampleapp.repository.ITaskRepository
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class,
    AppModuleBinds::class, ActivityModuleBinds::class])
interface TestAppComponent : AndroidInjector<TodoApplication> {

    val taskRepository: ITaskRepository

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): TestAppComponent
    }
}