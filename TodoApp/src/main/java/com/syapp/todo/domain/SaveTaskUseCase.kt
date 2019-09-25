package com.syapp.todo.domain

import com.syapp.todo.base.data.Result
import com.syapp.todo.repository.ITaskRepository
import com.syapp.todo.entity.Task
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