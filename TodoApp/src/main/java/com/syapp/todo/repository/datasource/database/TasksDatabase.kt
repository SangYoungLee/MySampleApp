package com.syapp.todo.repository.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.syapp.todo.entity.Task

/**
 * Created By lsy2014 on 2019-09-16
 */
@Database(entities = [Task::class], version = 2, exportSchema = false)
abstract class TasksDatabase : RoomDatabase() {

    abstract fun tasksDao(): TasksDao
}