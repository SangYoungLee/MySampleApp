package com.example.todoapp.repository.datasource

import com.example.todoapp.base.data.Result
import com.example.todoapp.entity.Task

interface DataSource {

    suspend fun getTasks(): Result<List<Task>>

    suspend fun getTask(taskId: String): Result<Task>

    suspend fun saveTask(task: Task): Result<Boolean>

    suspend fun deleteTask(taskId: String): Result<Boolean>

    suspend fun deleteAllTasks()

    suspend fun updateComplete(taskId: String, isComplete: Boolean): Result<Boolean>
}