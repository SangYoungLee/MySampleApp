package com.example.mysampleapp.base.repository

import com.example.mysampleapp.base.repository.database.TasksDao
import com.example.mysampleapp.entity.Task

/**
 * Created By lsy2014 on 2019-09-16
 */
class LocalTaskDataSource(
    private val tasksDao: TasksDao
) {

    fun getTasks(): List<Task> = tasksDao.getTasks()

    fun getTask(taskId: String): Task? = tasksDao.getTask(taskId)

    fun saveTask(task: Task) = tasksDao.insertTask(task)
}