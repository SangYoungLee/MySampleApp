package com.example.mysampleapp.base.data

enum class ChangeState {
    INIT,
    INSERT,
    FAIL,
    DELETE,
    UPDATE;

    fun isSuccess() = this != FAIL
}