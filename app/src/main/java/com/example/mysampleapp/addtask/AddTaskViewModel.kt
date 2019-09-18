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

    fun saveTask() {
        val title = title.value
        val content = content.value

        if (title.isNullOrEmpty() || content.isNullOrEmpty()) {
            return
        }

        val task = Task(title, content)

        viewModelScope.launch {
            _loading.value = true

            saveTaskUseCase.save(task)

            _loading.value = false
        }
    }

}