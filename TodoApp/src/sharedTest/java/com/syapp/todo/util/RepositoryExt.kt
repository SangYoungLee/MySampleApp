package com.syapp.todo.util

import com.syapp.todo.entity.Task
import com.syapp.todo.repository.ITaskRepository
import kotlinx.coroutines.runBlocking

fun ITaskRepository.saveTaskBlocking(task: Task) = runBlocking {
    this@saveTaskBlocking.saveTask(task)
}

fun ITaskRepository.deleteAllRunBlocking() = runBlocking {
    this@deleteAllRunBlocking.deleteAllTasks()
}