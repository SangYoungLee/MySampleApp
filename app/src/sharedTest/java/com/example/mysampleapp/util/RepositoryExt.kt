package com.example.mysampleapp.util

import com.example.mysampleapp.entity.Task
import com.example.mysampleapp.repository.ITaskRepository
import kotlinx.coroutines.runBlocking

fun ITaskRepository.saveTaskBlocking(task: Task) = runBlocking {
    this@saveTaskBlocking.saveTask(task)
}

fun ITaskRepository.deleteAllRunBlocking() = runBlocking {
    this@deleteAllRunBlocking.deleteAllTasks()
}