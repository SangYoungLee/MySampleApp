package com.syapp.todo.activity

import com.syapp.basecomponent.di.ActivityScope
import com.syapp.todo.base.viewmodel.ViewModelModuleBinds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModuleBinds {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentModuleBinds::class, ViewModelModuleBinds::class])
    abstract fun tasksActivity(): TasksActivity
}