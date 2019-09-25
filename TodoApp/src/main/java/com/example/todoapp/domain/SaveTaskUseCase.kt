package com.example.todoapp.domain

import com.example.todoapp.base.data.Result
import com.example.todoapp.repository.ITaskRepository
import com.example.todoapp.entity.Task
import javax.inject.Inject

/**
 * Created By lsy2014 on 2019-09-18
 */
class SaveTaskUseCase @Inject constructor(
    private val taskRepository: ITaskRepository
) {

    suspend fun save(task: Task): Result<Boolean> {
        return taskRepository.saveTask(task)
    }
}