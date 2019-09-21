package com.example.mysampleapp.repository.datasource

import com.example.mysampleapp.base.data.Result
import com.example.mysampleapp.entity.Task
import javax.inject.Inject

class RemoteTaskDataSource @Inject constructor(
) : DataSource {

    override suspend fun getTasks(): Result<List<Task>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getTask(taskId: String): Result<Task> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun saveTask(task: Task): Result<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deleteTask(taskId: String): Result<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateComplete(taskId: String, isComplete: Boolean): Result<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}