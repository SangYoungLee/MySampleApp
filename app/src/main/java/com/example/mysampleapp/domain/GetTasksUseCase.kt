package com.example.mysampleapp.domain

import com.example.mysampleapp.base.repository.ITaskRepository
import com.example.mysampleapp.entity.Task

/**
 * Created By lsy2014 on 2019-09-16
 */
class GetTasksUseCase(
    private val taskRepository: ITaskRepository
) {

    suspend operator fun invoke(): List<Task> {
        return taskRepository.getTasks()
    }
}