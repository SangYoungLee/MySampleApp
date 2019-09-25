package com.example.todoapp

import android.content.Context
import com.example.todoapp.activity.ActivityModuleBinds
import com.example.todoapp.activity.FragmentModuleBinds
import com.example.todoapp.application.AppModule
import com.example.todoapp.application.AppModuleBinds
import com.example.todoapp.application.TodoApplication
import com.example.todoapp.base.viewmodel.ViewModelModuleBinds
import com.example.todoapp.repository.ITaskRepository
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created By lsy2014 on 2019-09-24
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class,
    AppModuleBinds::class, ActivityModuleBinds::class, FragmentModuleBinds::class, ViewModelModuleBinds::class])
interface TestAppComponent : AndroidInjector<TodoApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): TestAppComponent
    }

    val taskRepository: ITaskRepository
}