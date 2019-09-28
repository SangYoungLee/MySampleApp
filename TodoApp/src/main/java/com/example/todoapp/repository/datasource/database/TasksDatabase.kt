package com.example.todoapp.repository.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.entity.Task

/**
 * Created By lsy2014 on 2019-09-16
 */
@Database(entities = [Task::class], version = 2, exportSchema = false)
abstract class TasksDatabase : RoomDatabase() {

    abstract fun tasksDao(): TasksDao
}