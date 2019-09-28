package com.example.todoapp.repository.datasource

import com.example.todoapp.base.data.Result
import com.example.todoapp.entity.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

object RemoteTaskDataSource : DataSource {

    private const val SERVICE_LATENCY_IN_MILLIS = 1000L

    private var TASKS_SERVICE_DATA = LinkedHashMap<String, Task>(2)

    private val ioDispatcher = Dispatchers.IO

    init {
        addTask("Build tower in Pisa", "Ground looks good, no foundation work required.")
        addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!")
    }

    private fun addTask(title: String, content: String) {
        val newTask = Task(title, content)
        TASKS_SERVICE_DATA[newTask.id] = newTask
    }

    override suspend fun getTasks(): Result<List<Task>> = withContext(ioDispatcher) {
        delay(SERVICE_LATENCY_IN_MILLIS)
        Result.Success(TASKS_SERVICE_DATA.values.toList())
    }

    override suspend fun getTask(taskId: String): Result<Task> = withContext(ioDispatcher) {
        delay(SERVICE_LATENCY_IN_MILLIS)
        TASKS_SERVICE_DATA[taskId]?.let {
            Result.Success(it)
        }
        Result.Failure(Exception("Not Found Task"))
    }

    override suspend fun saveTask(task: Task): Result<Boolean> = withContext(ioDispatcher) {
        delay(SERVICE_LATENCY_IN_MILLIS)
        TASKS_SERVICE_DATA[task.id] = task
        Result.Success(true)
    }

    override suspend fun deleteTask(taskId: String): Result<Boolean> = withContext(ioDispatcher) {
        delay(SERVICE_LATENCY_IN_MILLIS)
        if (TASKS_SERVICE_DATA.containsKey(taskId)) {
            TASKS_SERVICE_DATA.remove(taskId)
            Result.Success(true)
        }

        Result.Failure(Exception("Not Found Task"))
    }

    override suspend fun deleteAllTasks() {
        TASKS_SERVICE_DATA.clear()
    }

    override suspend fun updateComplete(taskId: String, isComplete: Boolean): Result<Boolean> = withContext(ioDispatcher) {
        delay(SERVICE_LATENCY_IN_MILLIS)
        TASKS_SERVICE_DATA[taskId]?.apply {
            this.isCompleted = isComplete
            return@withContext Result.Success(true)
        }

        Result.Failure(Exception("Not Found Task"))
    }
}