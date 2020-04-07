package com.syapp.todo.base.recyclerview

/**
 * Created By lsy2014 on 07/04/2020
 */
data class BaseItem<T>(
    val viewType: Int,
    val data: T
)