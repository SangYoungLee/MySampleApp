package com.example.todoapp.activity

import com.example.todoapp.addtask.AddTaskFragment
import com.example.todoapp.addtask.AddTaskModuleBinds
import com.example.todoapp.base.di.FragmentScope
import com.example.todoapp.detailtask.DetailTaskFragment
import com.example.todoapp.detailtask.DetailTaskModuleBinds
import com.example.todoapp.tasks.TasksFragment
import com.example.todoapp.tasks.TasksModuleBinds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModuleBinds {

    @FragmentScope
    @ContributesAndroidInjector(modules = [TasksModuleBinds::class])
    abstract fun tasksFragment(): TasksFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [AddTaskModuleBinds::class])
    abstract fun addTaskFragment(): AddTaskFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [DetailTaskModuleBinds::class])
    abstract fun detailTaskFragment(): DetailTaskFragment

}