package com.example.mysampleapp.domain

import com.example.mysampleapp.base.data.Result
import com.example.mysampleapp.repository.ITaskRepository
import javax.inject.Inject

/**
 * Created By lsy2014 on 2019-09-19
 */
class DeleteTaskUseCase @Inject constructor(
    private val taskRepository: ITaskRepository
) {
    suspend fun deleteTask(taskId: String): Result<Boolean> {
        return taskRepository.deleteTask(taskId)
    }
}