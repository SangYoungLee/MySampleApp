package com.syapp.todo.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.syapp.basecomponent.BaseFragment
import com.syapp.basecomponent.event.EventObserver
import com.syapp.todo.R
import com.syapp.todo.databinding.TasksFragmentBinding
import com.syapp.todo.util.showSnackbar
import javax.inject.Inject

class TasksFragment : BaseFragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: TasksFragmentBinding

    private val viewModel by viewModels<TasksViewModel> { viewModelFactory }

    private val args by navArgs<TasksFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TasksFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.fab_add)?.setOnClickListener {
            navigateToAddTask()
        }

        initMoveTask()
        initSnackbar()

        val tasksAdapter = TasksAdapter(viewModel)
        binding.rvTasks.adapter = tasksAdapter

        viewModel.refresh()
    }

    private fun initMoveTask() {
        viewModel.moveDetail.observe(viewLifecycleOwner, EventObserver {
            val action = TasksFragmentDirections
                .actionTasksFragmentDestToDetailTaskDest(it)
            findNavController().navigate(action)
        })
    }

    private fun initSnackbar() {
        viewModel.snackbarText.observe(viewLifecycleOwner, EventObserver {
            binding.root.showSnackbar(it)
        })
    }

    private fun navigateToAddTask() {
        val action = TasksFragmentDirections
            .actionTasksFragmentDestToAddTaskFragment("")
        findNavController().navigate(action)
    }
}