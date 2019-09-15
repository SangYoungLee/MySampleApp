package com.example.mysampleapp.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mysampleapp.base.BaseViewModel

class TasksViewModel : BaseViewModel() {
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun refresh() {

    }
}