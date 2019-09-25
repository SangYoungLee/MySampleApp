package com.syapp.todo.domain

import com.syapp.todo.base.data.Result
import com.syapp.todo.repository.ITaskRepository
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