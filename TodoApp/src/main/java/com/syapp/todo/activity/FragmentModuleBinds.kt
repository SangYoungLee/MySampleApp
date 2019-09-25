package com.syapp.todo.activity

import com.syapp.todo.addtask.AddTaskFragment
import com.syapp.todo.addtask.AddTaskModuleBinds
import com.syapp.todo.base.di.FragmentScope
import com.syapp.todo.detailtask.DetailTaskFragment
import com.syapp.todo.detailtask.DetailTaskModuleBinds
import com.syapp.todo.tasks.TasksFragment
import com.syapp.todo.tasks.TasksModuleBinds
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