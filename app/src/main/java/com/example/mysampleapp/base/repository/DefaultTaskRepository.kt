package com.example.mysampleapp.base.repository

import com.example.mysampleapp.entity.Task

/**
 * Created By lsy2014 on 2019-09-16
 */
class DefaultTaskRepository(
    private val localTaskDataSource: LocalTaskDataSource
) : ITaskRepository {

    override fun getTasks(): List<Task> {
        return localTaskDataSource.getTasks()
    }

    override fun getTask(taskId: String): Task? {
        return localTaskDataSource.getTask(taskId)
    }

    override fun saveTask(task: Task) {
        localTaskDataSource.saveTask(task)
    }
}