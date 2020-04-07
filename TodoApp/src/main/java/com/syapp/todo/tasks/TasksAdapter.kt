package com.syapp.todo.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import com.syapp.basecomponent.viewmodel.BaseViewModel
import com.syapp.todo.base.recyclerview.BaseRecycleAdapter
import com.syapp.todo.base.recyclerview.BaseViewHolder
import com.syapp.todo.base.recyclerview.HolderCreator
import com.syapp.todo.databinding.TasksListItemBinding
import com.syapp.todo.entity.Task

class TasksAdapter(tasksViewModel: TasksViewModel)
    : BaseRecycleAdapter<Task>(tasksViewModel) {

}

class TaskHolder(private val binding: TasksListItemBinding)
    : BaseViewHolder<Task>(binding.root) {

    class Creator : HolderCreator {
        override fun createHolder(parent: ViewGroup, viewModel: BaseViewModel?): BaseViewHolder<*> {
            val binding = TasksListItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
            binding.viewModel = viewModel as TasksViewModel
            return TaskHolder(binding)
        }
    }

    override fun onBindData(data: Task) {
        binding.task = data
        // Binding 기다리지않고, 즉시 실행
        binding.executePendingBindings()
    }
}