package com.example.todoapp.addtask

import androidx.lifecycle.ViewModel
import com.example.todoapp.base.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AddTaskModuleBinds {

    @Binds
    @IntoMap
    @ViewModelKey(AddTaskViewModel::class)
    abstract fun bindsAddTaskViewModel(addTaskViewModel: AddTaskViewModel): ViewModel
}