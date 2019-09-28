package com.syapp.todo

import android.content.Context
import com.syapp.todo.activity.ActivityModuleBinds
import com.syapp.todo.activity.FragmentModuleBinds
import com.syapp.todo.application.AppModule
import com.syapp.todo.application.AppModuleBinds
import com.syapp.todo.application.TodoApplication
import com.syapp.todo.base.viewmodel.ViewModelModuleBinds
import com.syapp.todo.repository.ITaskRepository
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