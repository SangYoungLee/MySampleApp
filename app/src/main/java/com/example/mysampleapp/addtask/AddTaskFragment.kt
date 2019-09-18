package com.example.mysampleapp.addtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mysampleapp.base.BaseFragment
import com.example.mysampleapp.base.data.ChangeState
import com.example.mysampleapp.base.viewmodel.getViewModelFactory
import com.example.mysampleapp.databinding.AddTaskFragmentBinding
import com.google.android.material.snackbar.Snackbar

class AddTaskFragment : BaseFragment() {

    private lateinit var binding: AddTaskFragmentBinding
    private val viewModel by viewModels<AddTaskViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddTaskFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTaskUpdate()
        initSnackbar()

        viewModel.start()
    }

    private fun initTaskUpdate() {
        viewModel.taskUpdatedEvent.observe(viewLifecycleOwner, Observer {
            val action = AddTaskFragmentDirections
                .actionAddTaskFragmentToTasksFragmentDest(ChangeState.INSERT)
            findNavController().navigate(action)
        })
    }

    private fun initSnackbar() {
        viewModel.snackbarText.observe(viewLifecycleOwner, Observer<String> {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        })
    }
}