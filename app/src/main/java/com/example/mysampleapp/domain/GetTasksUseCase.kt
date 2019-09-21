package com.example.mysampleapp.domain

import com.example.mysampleapp.repository.ITaskRepository
import com.example.mysampleapp.entity.Task
import javax.inject.Inject

/**
 * Created By lsy2014 on 2019-09-16
 */
class GetTasksUseCase @Inject constructor(
    private val taskRepository: ITaskRepository
) {

    suspend operator fun invoke(): List<Task> {
        return taskRepository.getTasks()
    }
}