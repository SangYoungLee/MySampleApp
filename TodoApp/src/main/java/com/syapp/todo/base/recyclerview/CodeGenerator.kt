package com.syapp.todo.base.recyclerview

/**
 * Created By lsy2014 on 07/04/2020
 */
object CodeGenerator {
    private var code: Int = 0

    fun generateCode(): Int {
        return ++code
    }
}