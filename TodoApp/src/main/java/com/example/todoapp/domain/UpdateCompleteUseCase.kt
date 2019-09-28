package com.example.todoapp.domain

import com.example.todoapp.base.data.Result
import com.example.todoapp.repository.ITaskRepository
import javax.inject.Inject

class UpdateCompleteUseCase @Inject constructor(
    private val taskRepository: ITaskRepository
) {

    suspend fun updateComplete(taskId: String, isComplete: Boolean): Result<Boolean> {
        return taskRepository.updateComplete(taskId, isComplete)
    }
}