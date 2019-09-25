package com.example.todoapp

import androidx.annotation.VisibleForTesting
import com.example.todoapp.base.data.Result
import com.example.todoapp.entity.Task
import com.example.todoapp.repository.ITaskRepository
import java.util.LinkedHashMap

class FakeRepository : ITaskRepository {

    var tasksServiceData: LinkedHashMap<String, Task> = LinkedHashMap()

    var shouldReturnError = false

    override suspend fun getTasks(): Result<List<Task>> {
        if (shouldReturnError) {
            return Result.Failure(Exception("Test exception"))
        }
        if (tasksServiceData.values.isNotEmpty()) {
            return Result.Success(tasksServiceData.values.toMutableList())
        }

        return Result.Failure(Exception("Data is Empty"))
    }

    override suspend fun getTask(taskId: String): Result<Task> {
        if (shouldReturnError) {
            return Result.Failure(Exception("Test exception"))
        }
        tasksServiceData[taskId]?.let {
            return Result.Success(it)
        }

        return Result.Failure(Exception("Data Not Found"))
    }

    override suspend fun saveTask(task: Task): Result<Boolean> {
        tasksServiceData[task.id] = task
        return Result.Success(true)
    }

    override suspend fun deleteTask(taskId: String): Result<Boolean> {
        tasksServiceData[taskId]?.let {
            tasksServiceData.remove(taskId)
            return Result.Success(true)
        }

        return Result.Failure(Exception("Data Not Exist"))
    }

    override suspend fun deleteAllTasks() {
        tasksServiceData.clear()
    }

    override suspend fun updateComplete(taskId: String, isComplete: Boolean): Result<Boolean> {
        tasksServiceData[taskId]?.let {
            val updatedTask = Task(it.title, it.contents, isComplete, it.id)
            tasksServiceData[taskId] = updatedTask

            return Result.Success(true)
        }

        return Result.Failure(Exception("Data Not Exist"))
    }

    @VisibleForTesting
    fun addTasks(vararg tasks: Task) {
        for (task in tasks) {
            tasksServiceData[task.id] = task
        }
    }
}