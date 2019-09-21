package com.example.mysampleapp.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.mysampleapp.base.data.Result
import com.example.mysampleapp.base.event.Event
import com.example.mysampleapp.base.viewmodel.BaseViewModel
import com.example.mysampleapp.domain.GetTasksUseCase
import com.example.mysampleapp.domain.UpdateCompleteUseCase
import com.example.mysampleapp.entity.Task
import kotlinx.coroutines.launch
import javax.inject.Inject

class TasksViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val updateCompleteUseCase: UpdateCompleteUseCase
) : BaseViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _taskList = MutableLiveData<List<Task>>().apply { value = emptyList() }
    val taskList: LiveData<List<Task>> = _taskList

    private val _moveDetail = MutableLiveData<Event<String>>()
    val moveDetail: LiveData<Event<String>> = _moveDetail

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    val isEmpty: LiveData<Boolean> = Transformations.map(taskList) { it.isEmpty() }

    fun refresh() {
        _dataLoading.value = true

        viewModelScope.launch {
            val taskDataList = getTasksUseCase()
            _taskList.value = taskDataList

            _dataLoading.value = false
        }
    }

    fun openTask(taskId: String) {
        _moveDetail.value = Event(taskId)
    }

    fun completeItem(taskId: String, isChecked: Boolean) = viewModelScope.launch {
        _dataLoading.value = true

        val result = updateCompleteUseCase.updateComplete(taskId, isChecked)

        _dataLoading.value = false

        if (result is Result.Failure) {
            _snackbarText.value = Event("업데이트에 실패했습니다.")
            return@launch
        }

        if (isChecked) {
            _snackbarText.value = Event("Task complete")
        } else {
            _snackbarText.value = Event("Task active")
        }
    }
}