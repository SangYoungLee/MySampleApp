package com.example.mysampleapp.repository.datasource

import com.example.mysampleapp.base.data.Result
import com.example.mysampleapp.entity.Task

interface DataSource {

    suspend fun getTasks(): Result<List<Task>>

    suspend fun getTask(taskId: String): Result<Task>

    suspend fun saveTask(task: Task): Result<Boolean>

    suspend fun deleteTask(taskId: String): Result<Boolean>

    suspend fun updateComplete(taskId: String, isComplete: Boolean): Result<Boolean>
}