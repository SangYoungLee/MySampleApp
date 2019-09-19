package com.example.mysampleapp.domain

import com.example.mysampleapp.base.data.Result
import com.example.mysampleapp.repository.ITaskRepository

class UpdateCompleteUseCase(
    private val taskRepository: ITaskRepository
) {

    suspend fun updateComplete(taskId: String, isComplete: Boolean): Result<Boolean> {
        return taskRepository.updateComplete(taskId, isComplete)
    }
}