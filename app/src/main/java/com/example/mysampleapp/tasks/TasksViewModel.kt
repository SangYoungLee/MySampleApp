package com.example.mysampleapp.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mysampleapp.base.viewmodel.BaseViewModel
import com.example.mysampleapp.domain.GetTasksUseCase
import com.example.mysampleapp.entity.Task

class TasksViewModel(
    private val getTasksUseCase: GetTasksUseCase
) : BaseViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _taskList = MutableLiveData<List<Task>>()
    val taskList: LiveData<List<Task>> = _taskList

    fun refresh() {
        _dataLoading.value = true

        val taskDataList = getTasksUseCase()
        _taskList.value = taskDataList

        _dataLoading.value = false
    }

    fun onAddClick() {

    }
}