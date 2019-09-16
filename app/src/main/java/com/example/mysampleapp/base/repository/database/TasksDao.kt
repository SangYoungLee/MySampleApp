package com.example.mysampleapp.base.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
}