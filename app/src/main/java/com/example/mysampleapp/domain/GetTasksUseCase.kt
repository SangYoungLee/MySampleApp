package com.example.mysampleapp.domain

import com.example.mysampleapp.base.data.Result
import com.example.mysampleapp.entity.Task
import com.example.mysampleapp.repository.ITaskRepository
import javax.inject.Inject

/**
 * Created By lsy2014 on 2019-09-16
 */
class GetTasksUseCase @Inject constructor(
    private val taskRepository: ITaskRepository
) {

    suspend fun getTasks(): Result<List<Task>> {
        return taskRepository.getTasks()
    }
}