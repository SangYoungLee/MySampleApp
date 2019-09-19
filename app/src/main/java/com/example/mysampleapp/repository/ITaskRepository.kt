package com.example.mysampleapp.repository

import com.example.mysampleapp.base.data.Result
import com.example.mysampleapp.entity.Task

/**
 * Created By lsy2014 on 2019-09-16
 */
interface ITaskRepository {

    suspend fun getTasks(): List<Task>

    suspend fun getTask(taskId: String): Result<Task>

    suspend fun saveTask(task: Task): Result<Boolean>

    suspend fun deleteTask(taskId: String): Result<Boolean>

    suspend fun updateComplete(taskId: String, isComplete: Boolean): Result<Boolean>
}