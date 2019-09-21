package com.example.mysampleapp.activity

import com.example.mysampleapp.addtask.AddTaskFragment
import com.example.mysampleapp.addtask.AddTaskModuleBinds
import com.example.mysampleapp.base.di.FragmentScope
import com.example.mysampleapp.detailtask.DetailTaskFragment
import com.example.mysampleapp.detailtask.DetailTaskModuleBinds
import com.example.mysampleapp.tasks.TasksFragment
import com.example.mysampleapp.tasks.TasksModuleBinds
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