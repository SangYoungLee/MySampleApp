package com.syapp.todo.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.syapp.todo.R
import com.syapp.todo.base.BaseRecycleAdapter
import com.syapp.todo.base.BaseViewHolder
import com.syapp.todo.databinding.TasksListItemBinding
import com.syapp.todo.entity.Task

class TasksAdapter(private val tasksViewModel: TasksViewModel)
    : BaseRecycleAdapter<Task, TasksAdapter.TaskHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val binding = TasksListItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        binding.viewModel = tasksViewModel
        return TaskHolder(binding)
    }

    inner class TaskHolder(private val binding: TasksListItemBinding)
        : BaseViewHolder<Task>(binding.root) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)

        override fun onBindData(data: Task) {
            binding.task = data
            // Binding 기다리지않고, 즉시 실행
            binding.executePendingBindings()
        }

        override fun onRecycled() {
            tvTitle.text = ""
        }
    }
}