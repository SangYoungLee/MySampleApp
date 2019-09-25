package com.example.todoapp.util

import com.example.todoapp.entity.Task
import com.example.todoapp.repository.ITaskRepository
import kotlinx.coroutines.runBlocking

fun ITaskRepository.saveTaskBlocking(task: Task) = runBlocking {
    this@saveTaskBlocking.saveTask(task)
}

fun ITaskRepository.deleteAllRunBlocking() = runBlocking {
    this@deleteAllRunBlocking.deleteAllTasks()
}