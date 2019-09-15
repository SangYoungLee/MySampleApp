package com.example.mysampleapp.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mysampleapp.R
import com.example.mysampleapp.base.BaseRecycleAdapter
import com.example.mysampleapp.base.BaseViewHolder
import com.example.mysampleapp.entity.Task

class TasksAdapter : BaseRecycleAdapter<Task, TasksAdapter.TaskHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        return TaskHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_task, parent, false))
    }

    inner class TaskHolder(itemView: View) : BaseViewHolder<Task>(itemView) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)

        override fun onBindData(data: Task) {
            tvTitle.text = data.title
        }

        override fun onRecycled() {
            tvTitle.text = ""
        }
    }
}