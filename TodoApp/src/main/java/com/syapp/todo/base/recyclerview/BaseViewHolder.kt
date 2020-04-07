package com.syapp.todo.base.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    abstract fun onBindData(data: Any)

    fun onRecycled() {

    }
}