package com.example.mysampleapp.domain

import com.example.mysampleapp.base.data.Result
import com.example.mysampleapp.repository.ITaskRepository
import javax.inject.Inject

class UpdateCompleteUseCase @Inject constructor(
    private val taskRepository: ITaskRepository
) {

    suspend fun updateComplete(taskId: String, isComplete: Boolean): Result<Boolean> {
        return taskRepository.updateComplete(taskId, isComplete)
    }
}