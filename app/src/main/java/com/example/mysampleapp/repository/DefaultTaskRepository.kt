package com.example.mysampleapp.repository

import com.example.mysampleapp.application.AppModule
import com.example.mysampleapp.base.data.Result
import com.example.mysampleapp.entity.Task
import com.example.mysampleapp.repository.datasource.DataSource
import javax.inject.Inject

/**
 * Created By lsy2014 on 2019-09-16
 */
class DefaultTaskRepository @Inject constructor(
    @AppModule.LocalDataSource private val localTaskDataSource: DataSource,
    @AppModule.RemoteDataSource private val remoteTaskDataSource: DataSource
) : ITaskRepository {

    override suspend fun getTasks(): Result<List<Task>> {
        return localTaskDataSource.getTasks()
    }

    override suspend fun getTask(taskId: String): Result<Task> {
        return localTaskDataSource.getTask(taskId)
    }

    override suspend fun saveTask(task: Task): Result<Boolean> {
        return localTaskDataSource.saveTask(task)
    }

    override suspend fun deleteTask(taskId: String): Result<Boolean> {
        return localTaskDataSource.deleteTask(taskId)
    }

    override suspend fun updateComplete(taskId: String, isComplete: Boolean): Result<Boolean> {
        return localTaskDataSource.updateComplete(taskId, isComplete)
    }

}