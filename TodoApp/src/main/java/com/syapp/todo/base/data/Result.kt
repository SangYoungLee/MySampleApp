package com.syapp.todo.base.data

/**
 * Created By lsy2014 on 2019-09-18
 */
sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val exception: Exception) : Result<Nothing>()

    fun isSuccess(): Boolean = this is Success

    fun getValue(): R = (this as Success).data
}