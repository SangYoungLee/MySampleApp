package com.syapp.todo.repository

import com.syapp.todo.base.data.Result
import com.syapp.todo.entity.Task

/**
 * Created By lsy2014 on 2019-09-16
 */
interface ITaskRepository {

    suspend fun getTasks(): Result<List<Task>>

    suspend fun getTask(taskId: String): Result<Task>

    suspend fun saveTask(task: Task): Result<Boolean>

    suspend fun deleteTask(taskId: String): Result<Boolean>

    suspend fun deleteAllTasks()

    suspend fun updateComplete(taskId: String, isComplete: Boolean): Result<Boolean>
}