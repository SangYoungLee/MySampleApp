package com.example.mysampleapp

import android.content.Context
import com.example.mysampleapp.activity.ActivityModuleBinds
import com.example.mysampleapp.activity.FragmentModuleBinds
import com.example.mysampleapp.application.AppModule
import com.example.mysampleapp.application.AppModuleBinds
import com.example.mysampleapp.application.TodoApplication
import com.example.mysampleapp.base.viewmodel.ViewModelModuleBinds
import com.example.mysampleapp.repository.ITaskRepository
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