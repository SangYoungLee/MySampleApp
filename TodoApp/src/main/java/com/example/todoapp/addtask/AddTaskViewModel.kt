package com.example.todoapp.addtask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.base.data.Result
import com.example.todoapp.base.viewmodel.BaseViewModel
import com.example.todoapp.domain.GetTaskUseCase
import com.example.todoapp.domain.SaveTaskUseCase
import com.example.todoapp.entity.Task
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddTaskViewModel @Inject constructor(
    private val saveTaskUseCase: SaveTaskUseCase,
    private val getTaskUseCase: GetTaskUseCase
) : BaseViewModel() {

    // EditText Two-way DataBinding
    val title = MutableLiveData<String>()
    val content = MutableLiveData<String>()

    private val _loading = MutableLiveData<Boolean>().apply { value = false }
    val loading: LiveData<Boolean> = _loading

    private val _snackbarText = MutableLiveData<String>()
    val snackbarText: LiveData<String> = _snackbarText

    private val _taskUpdatedEvent = MutableLiveData<Any>()
    val taskUpdatedEvent: LiveData<Any> = _taskUpdatedEvent

    private var taskId: String? = ""
    private var isNewTask: Boolean = false
    private var taskCompleted: Boolean = false

    fun start(taskId: String?) {
        this.taskId = taskId

        if (taskId.isNullOrEmpty()) {
            isNewTask = true
            return
        }

        viewModelScope.launch {
            _loading.value = true

            val result = getTaskUseCase.getTask(taskId)

            _loading.value = false

            if (result is Result.Failure) {
                isNewTask = true
                return@launch
            }

            val task = result.getValue()

            title.value = task.title
            content.value = task.contents
        }
    }

    fun saveTask() {
        val title = title.value
        val content = content.value

        if (title.isNullOrEmpty() || content.isNullOrEmpty()) {
            _snackbarText.value = "타이틀 또는 컨텐츠가 비어있습니다"
            return
        }

        val task =
            if (isNewTask) Task(title, content)
            else Task(title, content, taskCompleted, taskId!!)

        viewModelScope.launch {
            _loading.value = true

            val result = saveTaskUseCase.save(task)

            _loading.value = false

            if (result.isSuccess()) {
                _taskUpdatedEvent.value = Any()
            } else {
                _snackbarText.value = "저장에 실패했습니다."
            }
        }
    }

}