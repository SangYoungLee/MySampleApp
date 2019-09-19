package com.example.mysampleapp.domain

import com.example.mysampleapp.base.data.Result
import com.example.mysampleapp.entity.Task
import com.example.mysampleapp.repository.ITaskRepository

/**
 * Created By lsy2014 on 2019-09-19
 */
class GetTaskUseCase(
    private val taskRepository: ITaskRepository
) {

    suspend fun getTask(taskId: String): Result<Task> {
        return taskRepository.getTask(taskId)
    }
}