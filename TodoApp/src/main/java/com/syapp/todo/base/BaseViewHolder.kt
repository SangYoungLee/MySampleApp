package com.syapp.todo.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    abstract fun onBindData(data: T)

    abstract fun onRecycled()
}