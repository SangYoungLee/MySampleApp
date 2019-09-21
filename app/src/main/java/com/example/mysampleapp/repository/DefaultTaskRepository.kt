package com.example.mysampleapp.repository

import com.example.mysampleapp.base.data.Result
import com.example.mysampleapp.entity.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created By lsy2014 on 2019-09-16
 */
class DefaultTaskRepository @Inject constructor(
    private val localTaskDataSource: LocalTaskDataSource
) : ITaskRepository {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun getTasks(): List<Task> {
        return withContext(ioDispatcher) {
            localTaskDataSource.getTasks()
        }
    }

    override suspend fun getTask(taskId: String): Result<Task> {
        return withContext(ioDispatcher) {
            try {
                val task: Task? = localTaskDataSource.getTask(taskId)
                if (task != null) {
                    Result.Success(task)
                } else {
                    Result.Failure(IllegalStateException("not found Task"))
                }
            } catch (e: Exception) {
                Result.Failure(e)
            }
        }
    }

    override suspend fun saveTask(task: Task): Result<Boolean> {
        return withContext(ioDispatcher) {
            try {
                localTaskDataSource.saveTask(task)
                Result.Success(true)
            } catch (e: Exception) {
                Result.Failure(e)
            }
        }
    }

    override suspend fun deleteTask(taskId: String): Result<Boolean> {
        return withContext(ioDispatcher) {
            try {
                localTaskDataSource.deleteTask(taskId)
                Result.Success(true)
            } catch (e: Exception) {
                Result.Failure(e)
            }
        }
    }

    override suspend fun updateComplete(taskId: String, isComplete: Boolean): Result<Boolean> = withContext(ioDispatcher) {
        try {
            localTaskDataSource.updateComplete(taskId, isComplete)
            Result.Success(true)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

}