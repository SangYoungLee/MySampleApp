package com.example.mysampleapp.domain

import com.example.mysampleapp.base.data.Result
import com.example.mysampleapp.repository.ITaskRepository
import com.example.mysampleapp.entity.Task
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