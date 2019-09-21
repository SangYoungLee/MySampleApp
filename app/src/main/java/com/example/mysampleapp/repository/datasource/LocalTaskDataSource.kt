package com.example.mysampleapp.repository.datasource

import com.example.mysampleapp.base.data.Result
import com.example.mysampleapp.repository.datasource.database.TasksDao
import com.example.mysampleapp.entity.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created By lsy2014 on 2019-09-16
 */
class LocalTaskDataSource(
    private val tasksDao: TasksDao
): DataSource {

    private val ioDispatcher = Dispatchers.IO

    override suspend fun getTasks(): Result<List<Task>> {
        return withContext(ioDispatcher) {
            try {
                Result.Success(tasksDao.getTasks())
            } catch (e: Exception) {
                Result.Failure(e)
            }
        }
    }

    override suspend fun getTask(taskId: String): Result<Task> = withContext(ioDispatcher) {
        try {
            Result.Success(tasksDao.getTask(taskId))
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    override suspend fun saveTask(task: Task): Result<Boolean> = withContext(ioDispatcher) {
        try {
            tasksDao.insertTask(task)
            Result.Success(true)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    override suspend fun deleteTask(taskId: String): Result<Boolean> = withContext(ioDispatcher) {
        try {
            tasksDao.deleteByTaskId(taskId)
            Result.Success(true)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    override suspend fun deleteAllTasks() = withContext(ioDispatcher) {
        tasksDao.deleteAllTasks()
    }

    override suspend fun updateComplete(taskId: String, isComplete: Boolean): Result<Boolean> = withContext(ioDispatcher) {
        try {
            tasksDao.updateComplete(taskId, isComplete)
            Result.Success(true)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}