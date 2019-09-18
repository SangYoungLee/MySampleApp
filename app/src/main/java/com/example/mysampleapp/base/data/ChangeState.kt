package com.example.mysampleapp.base.data

enum class ChangeState {
    INIT,
    INSERT,
    FAIL,
    UPDATE;

    fun isSuccess() = this != FAIL
}