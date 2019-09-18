package com.example.mysampleapp.repository

import com.example.mysampleapp.entity.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created By lsy2014 on 2019-09-16
 */
class DefaultTaskRepository(
    private val localTaskDataSource: LocalTaskDataSource
) : ITaskRepository {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun getTasks(): List<Task> {
        return withContext(ioDispatcher) {
            localTaskDataSource.getTasks()
        }
    }

    override suspend fun getTask(taskId: String): Task? {
        return withContext(ioDispatcher) {
            localTaskDataSource.getTask(taskId)
        }
    }

    override suspend fun saveTask(task: Task) {
        withContext(ioDispatcher) {
            localTaskDataSource.saveTask(task)
        }
    }
}