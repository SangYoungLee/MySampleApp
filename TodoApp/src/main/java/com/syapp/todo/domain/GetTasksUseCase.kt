package com.syapp.todo.domain

import com.syapp.todo.base.data.Result
import com.syapp.todo.entity.Task
import com.syapp.todo.repository.ITaskRepository
import javax.inject.Inject

/**
 * Created By lsy2014 on 2019-09-16
 */
class GetTasksUseCase @Inject constructor(
    private val taskRepository: ITaskRepository
) {

    suspend fun getTasks(): Result<List<Task>> {
        return taskRepository.getTasks()
    }
}