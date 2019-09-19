package com.example.mysampleapp.base.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mysampleapp.addtask.AddTaskViewModel
import com.example.mysampleapp.application.TodoApplication
import com.example.mysampleapp.detailtask.DetailTaskViewModel
import com.example.mysampleapp.domain.*
import com.example.mysampleapp.repository.ITaskRepository
import com.example.mysampleapp.tasks.TasksViewModel

/**
 * Created By lsy2014 on 2019-09-16
 */
class ViewModelFactory(private val taskRepository: ITaskRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return (when {
            modelClass.isAssignableFrom(TasksViewModel::class.java) -> TasksViewModel(
                GetTasksUseCase(taskRepository),
                UpdateCompleteUseCase((taskRepository))
            )

            modelClass.isAssignableFrom(AddTaskViewModel::class.java) -> AddTaskViewModel(
                SaveTaskUseCase(taskRepository)
            )

            modelClass.isAssignableFrom(DetailTaskViewModel::class.java) -> DetailTaskViewModel(
                GetTaskUseCase(taskRepository),
                DeleteTaskUseCase(taskRepository)
            )

            else -> throw IllegalStateException("Not matching ViewModel Class")
        } as T)
    }
}

fun Fragment.getViewModelFactory(): ViewModelProvider.Factory {
    return ViewModelFactory((context!!.applicationContext as TodoApplication).taskRepository)
}