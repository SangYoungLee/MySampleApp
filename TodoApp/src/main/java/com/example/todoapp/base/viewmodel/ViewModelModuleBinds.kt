package com.example.todoapp.base.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModuleBinds {

    @Binds
    abstract fun bindsViewModelFactory(todoViewModelFactory: TodoViewModelFactory): ViewModelProvider.Factory
}