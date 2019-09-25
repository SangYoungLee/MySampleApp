package com.example.todoapp.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecycleAdapter<T, VH: BaseViewHolder<T>> : RecyclerView.Adapter<VH>() {

    var items: List<T>? = null

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBindData(items?.getOrNull(position) ?: return)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }
}