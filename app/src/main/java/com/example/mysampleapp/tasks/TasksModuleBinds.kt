package com.example.mysampleapp.tasks

import androidx.lifecycle.ViewModel
import com.example.mysampleapp.base.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TasksModuleBinds {

    @IntoMap
    @Binds
    @ViewModelKey(TasksViewModel::class)
    abstract fun bindsTasksViewModel(tasksViewModel: TasksViewModel): ViewModel
}