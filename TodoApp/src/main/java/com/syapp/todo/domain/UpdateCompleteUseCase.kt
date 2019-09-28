package com.syapp.todo.domain

import com.syapp.todo.base.data.Result
import com.syapp.todo.repository.ITaskRepository
import javax.inject.Inject

class UpdateCompleteUseCase @Inject constructor(
    private val taskRepository: ITaskRepository
) {

    suspend fun updateComplete(taskId: String, isComplete: Boolean): Result<Boolean> {
        return taskRepository.updateComplete(taskId, isComplete)
    }
}