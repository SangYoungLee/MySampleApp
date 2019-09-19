package com.example.mysampleapp.repository.database

import androidx.room.*
import com.example.mysampleapp.entity.Task

/**
 * Created By lsy2014 on 2019-09-16
 */
@Dao
interface TasksDao {

    @Query("SELECT * FROM Tasks")
    fun getTasks(): List<Task>

    @Query("SELECT * FROM Tasks WHERE taskId = :taskId")
    fun getTask(taskId: String): Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Query("DELETE FROM Tasks WHERE taskId = :taskId")
    fun deleteByTaskId(taskId: String)
}