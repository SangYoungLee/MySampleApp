package com.example.todoapp.base.data

enum class ChangeState {
    INIT,
    INSERT,
    FAIL,
    DELETE,
    UPDATE;

    fun isSuccess() = this != FAIL
}