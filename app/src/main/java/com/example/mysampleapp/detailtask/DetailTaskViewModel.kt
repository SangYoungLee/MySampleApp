package com.example.mysampleapp.detailtask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mysampleapp.base.data.Result
import com.example.mysampleapp.base.viewmodel.BaseViewModel
import com.example.mysampleapp.domain.GetTaskUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created By lsy2014 on 2019-09-19
 */
class DetailTaskViewModel(
    private val getTaskUseCase: GetTaskUseCase
) : BaseViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _content = MutableLiveData<String>()
    val content: LiveData<String> = _content

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _snackbarText = MutableLiveData<String>()
    val snackbarText: LiveData<String> = _snackbarText

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    fun start(taskId: String?) {
        if (taskId.isNullOrEmpty()) {
            _snackbarText.value = "Task Id가 올바르지 않습니다."
            return
        }

        viewModelScope.launch {
            _loading.value = true

            val taskResult = getTaskUseCase.getTask(taskId)

            _loading.value = false

            if (taskResult is Result.Failure) {
                _snackbarText.value = "Task를 불러오지 못했습니다.\n이전 화면으로 돌아갑니다."

                delay(1000)

                _error.value = true
                return@launch
            }

            val task = taskResult.getValue()

            _title.value = task.title
            _content.value = task.contents
        }
    }
}