package com.example.mysampleapp.tasks

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mysampleapp.entity.Task

@BindingAdapter("items")
fun setItems(recyclerView: RecyclerView, items: List<Task>) {
    (recyclerView.adapter as? TasksAdapter)?.apply {
        this.items = items
        notifyDataSetChanged()
    }
}