package com.example.mysampleapp.repository

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mysampleapp.application.Migrations.MIGRATION_1_2
import com.example.mysampleapp.repository.database.TasksDatabase

/**
 * Created By lsy2014 on 2019-09-16
 */
object ServiceLocator {

    private var taskRepository: ITaskRepository? = null
    private var taskDatabase: TasksDatabase? = null

    fun provideTaskRepository(context: Context): ITaskRepository {
        return taskRepository
            ?: createTaskRepository(context)
    }

    private fun createTaskRepository(context: Context): ITaskRepository {
        return DefaultTaskRepository(
            createLocalDataSource(
                context
            )
        ).also {
            taskRepository = it
        }
    }

    private fun createLocalDataSource(context: Context): LocalTaskDataSource {
        val database = taskDatabase
            ?: createDatabase(context)
        return LocalTaskDataSource(database.tasksDao())
    }

    private fun createDatabase(context: Context): TasksDatabase {
        val database = Room.databaseBuilder(context.applicationContext,
            TasksDatabase::class.java,
            "Tasks.db")
            .addMigrations(MIGRATION_1_2)
            .build()
        taskDatabase = database

        return database
    }
}