package com.example.mysampleapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tasks")
data class Task(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "contents") val contents: String,
    @PrimaryKey @ColumnInfo(name = "taskId") val id: String? = UUID.randomUUID().toString()
)