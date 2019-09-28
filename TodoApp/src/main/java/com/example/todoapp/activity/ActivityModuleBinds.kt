package com.example.todoapp.activity

import com.example.todoapp.base.di.ActivityScope
import com.example.todoapp.base.viewmodel.ViewModelModuleBinds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModuleBinds {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentModuleBinds::class, ViewModelModuleBinds::class])
    abstract fun tasksActivity(): TasksActivity
}