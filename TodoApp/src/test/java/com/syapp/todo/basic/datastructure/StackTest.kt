package com.syapp.todo.basic.datastructure

import org.junit.Test

class StackTest {

    @Test
    fun testStack() {
        val stack = Stack<Int>()
        for (i in 0..13) {
            println("push $i")
            stack.push(i)
        }

        println()
        println(stack)
        println()

        for (i in 0..13) {
            println("pop ${stack.pop()}")
        }

        println()
        println(stack)
    }
}