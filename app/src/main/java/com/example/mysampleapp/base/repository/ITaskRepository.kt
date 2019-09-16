package com.example.mysampleapp.base.repository

import com.example.mysampleapp.entity.Task

/**
 * Created By lsy2014 on 2019-09-16
 */
interface ITaskRepository {

    fun getTasks(): List<Task>

    fun getTask(taskId: String): Task?

    fun saveTask(task: Task)
}