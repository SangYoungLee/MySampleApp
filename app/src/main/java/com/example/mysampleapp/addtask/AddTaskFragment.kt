package com.example.mysampleapp.addtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mysampleapp.base.BaseFragment
import com.example.mysampleapp.base.data.ChangeState
import com.example.mysampleapp.databinding.AddTaskFragmentBinding
import com.example.mysampleapp.util.showSnackbar
import javax.inject.Inject

class AddTaskFragment : BaseFragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: AddTaskFragmentBinding
    private val viewModel by viewModels<AddTaskViewModel> { viewModelFactory }

    private val args by navArgs<AddTaskFragmentArgs>()

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

        viewModel.start(args.taskId)
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
            binding.root.showSnackbar(it)
        })
    }
}