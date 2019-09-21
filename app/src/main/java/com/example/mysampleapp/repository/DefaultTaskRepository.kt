package com.example.mysampleapp.repository

import android.util.Log
import com.example.mysampleapp.application.AppModule
import com.example.mysampleapp.base.data.Result
import com.example.mysampleapp.entity.Task
import com.example.mysampleapp.repository.datasource.DataSource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By lsy2014 on 2019-09-16
 */
class DefaultTaskRepository @Inject constructor(
    @AppModule.LocalDataSource private val localTaskDataSource: DataSource,
    @AppModule.RemoteDataSource private val remoteTaskDataSource: DataSource
) : ITaskRepository {

    private val tag = DefaultTaskRepository::class.java.simpleName

    override suspend fun getTasks(): Result<List<Task>> {
        when (val remoteTasks = remoteTaskDataSource.getTasks()) {
            is Result.Failure -> Log.w(tag, "Get Remote Tasks Failed")
            is Result.Success -> {
                localTaskDataSource.deleteAllTasks()
                remoteTasks.data.forEach {
                    localTaskDataSource.saveTask(it)
                }
                return remoteTasks
            }
        }

        return localTaskDataSource.getTasks()
    }

    override suspend fun getTask(taskId: String): Result<Task> {
        when (val remoteTask = remoteTaskDataSource.getTask(taskId)) {
            is Result.Failure -> Log.w(tag, "Get Remote Task Failed")
            is Result.Success -> return remoteTask
        }

        return localTaskDataSource.getTask(taskId)
    }

    override suspend fun saveTask(task: Task): Result<Boolean> {
        coroutineScope {
            launch { remoteTaskDataSource.saveTask(task) }
            launch { localTaskDataSource.saveTask(task) }
        }

        return Result.Success(true)
    }

    override suspend fun deleteTask(taskId: String): Result<Boolean> {
        coroutineScope {
            launch { remoteTaskDataSource.deleteTask(taskId) }
            launch { localTaskDataSource.deleteTask(taskId) }
        }

        return Result.Success(true)
    }

    override suspend fun updateComplete(taskId: String, isComplete: Boolean): Result<Boolean> {
        coroutineScope {
            launch { remoteTaskDataSource.updateComplete(taskId, isComplete) }
            launch { localTaskDataSource.updateComplete(taskId, isComplete) }
        }

        return Result.Success(true)
    }

}