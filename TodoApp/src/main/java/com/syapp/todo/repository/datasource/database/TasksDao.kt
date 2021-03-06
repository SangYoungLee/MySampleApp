package com.syapp.todo.repository.datasource.database

import androidx.room.*
import com.syapp.todo.entity.Task

/**
 * Created By lsy2014 on 2019-09-16
 */
@Dao
interface TasksDao {

    @Query("SELECT * FROM Tasks")
    fun getTasks(): List<Task>

    @Query("SELECT * FROM Tasks WHERE taskId = :taskId")
    fun getTask(taskId: String): Task

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Query("DELETE FROM Tasks WHERE taskId = :taskId")
    fun deleteByTaskId(taskId: String)

    @Query("DELETE FROM Tasks")
    fun deleteAllTasks()

    @Query("UPDATE tasks SET isCompleted = :isCompleted WHERE taskId = :taskId")
    fun updateComplete(taskId: String, isCompleted: Boolean)
}