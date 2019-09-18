package com.example.mysampleapp.addtask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mysampleapp.base.viewmodel.BaseViewModel
import com.example.mysampleapp.domain.SaveTaskUseCase
import com.example.mysampleapp.entity.Task
import kotlinx.coroutines.launch

class AddTaskViewModel(
    private val saveTaskUseCase: SaveTaskUseCase
) : BaseViewModel() {

    // EditText Two-way DataBinding
    val title = MutableLiveData<String>()
    val content = MutableLiveData<String>()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _snackbarText = MutableLiveData<String>()
    val snackbarText: LiveData<String> = _snackbarText

    private val _taskUpdatedEvent = MutableLiveData<Any>()
    val taskUpdatedEvent: LiveData<Any> = _taskUpdatedEvent

    fun start() {
        _loading.value = false
    }

    fun saveTask() {
        val title = title.value
        val content = content.value

        if (title.isNullOrEmpty() || content.isNullOrEmpty()) {
            _snackbarText.value = "타이틀 또는 컨텐츠가 비어있습니다"
            return
        }

        val task = Task(title, content)

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