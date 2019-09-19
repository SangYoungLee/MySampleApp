package com.example.mysampleapp.detailtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mysampleapp.base.BaseFragment
import com.example.mysampleapp.base.data.ChangeState
import com.example.mysampleapp.base.viewmodel.getViewModelFactory
import com.example.mysampleapp.databinding.DetailTaskFragmentBinding
import com.example.mysampleapp.util.showSnackbar

class DetailTaskFragment : BaseFragment() {

    private val viewModel by viewModels<DetailTaskViewModel> { getViewModelFactory() }
    private lateinit var binding: DetailTaskFragmentBinding

    private val args by navArgs<DetailTaskFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailTaskFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSnackbar()
        initError()

        viewModel.start(arguments?.let { args.taskId } ?: "")
    }

    private fun initSnackbar() {
        viewModel.snackbarText.observe(viewLifecycleOwner, Observer {
            binding.root.showSnackbar(it)
        })
    }

    private fun initError() {
        viewModel.error.observe(viewLifecycleOwner, Observer {
            val action = DetailTaskFragmentDirections
                .actionDetailTaskDestToTasksFragmentDest(ChangeState.FAIL)
            findNavController().navigate(action)
        })
    }
}