package com.example.mysampleapp.activity

import com.example.mysampleapp.base.di.ActivityScope
import com.example.mysampleapp.base.viewmodel.ViewModelModuleBinds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModuleBinds {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentModuleBinds::class, ViewModelModuleBinds::class])
    abstract fun tasksActivity(): TasksActivity
}