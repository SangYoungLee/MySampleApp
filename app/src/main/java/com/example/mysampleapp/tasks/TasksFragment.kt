package com.example.mysampleapp.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.mysampleapp.R
import com.example.mysampleapp.base.BaseFragment
import com.example.mysampleapp.base.viewmodel.getViewModelFactory
import com.example.mysampleapp.databinding.TasksFragmentBinding

class TasksFragment : BaseFragment() {

    private lateinit var binding: TasksFragmentBinding

    private val viewModel by viewModels<TasksViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.tasks_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refresh()
    }
}