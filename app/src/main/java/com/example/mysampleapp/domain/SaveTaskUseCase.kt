package com.example.mysampleapp.domain

import com.example.mysampleapp.repository.ITaskRepository
import com.example.mysampleapp.entity.Task

/**
 * Created By lsy2014 on 2019-09-18
 */
class SaveTaskUseCase(
    private val taskRepository: ITaskRepository
) {

    suspend fun save(task: Task) {
        taskRepository.saveTask(task)
    }
}