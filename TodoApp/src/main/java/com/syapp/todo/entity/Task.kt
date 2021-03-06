package com.syapp.todo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tasks")
data class Task(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "contents") val contents: String,
    @ColumnInfo(name = "isCompleted") var isCompleted: Boolean = false,
    @PrimaryKey @ColumnInfo(name = "taskId") val id: String = UUID.randomUUID().toString()
) {

    fun isValid() = title.isNotEmpty() && contents.isNotEmpty()
}