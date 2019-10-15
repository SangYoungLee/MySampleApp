package com.syapp.todo.basic

import com.syapp.todo.entity.Task
import org.junit.Test

class KotlinBasicTest {

    @Test
    fun testAlso() {
        val task = Task("title", "contents").also {
            it.isCompleted = true
        }

        println(task.isCompleted)
    }
}