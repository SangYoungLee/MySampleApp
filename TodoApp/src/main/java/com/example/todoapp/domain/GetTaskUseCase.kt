package com.example.todoapp.domain

import com.example.todoapp.base.data.Result
import com.example.todoapp.entity.Task
import com.example.todoapp.repository.ITaskRepository
import javax.inject.Inject

/**
 * Created By lsy2014 on 2019-09-19
 */
class GetTaskUseCase @Inject constructor(
    private val taskRepository: ITaskRepository
) {

    suspend fun getTask(taskId: String): Result<Task> {
        return taskRepository.getTask(taskId)
    }
}