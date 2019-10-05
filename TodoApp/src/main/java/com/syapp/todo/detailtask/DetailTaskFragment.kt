package com.syapp.todo.detailtask

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.syapp.todo.R
import com.syapp.todo.base.BaseFragment
import com.syapp.todo.databinding.DetailTaskFragmentBinding
import com.syapp.todo.util.showSnackbar
import javax.inject.Inject

class DetailTaskFragment : BaseFragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<DetailTaskViewModel> { viewModelFactory }
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

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSnackbar()
        initMoveBack()
        initEditBtn()

        viewModel.start(args.taskId)
    }

    private fun initSnackbar() {
        viewModel.snackbarText.observe(viewLifecycleOwner, Observer {
            binding.root.showSnackbar(it)
        })
    }

    private fun initMoveBack() {
        viewModel.moveBack.observe(viewLifecycleOwner, Observer {
            val action = DetailTaskFragmentDirections
                .actionDetailTaskDestToTasksFragmentDest(it)
            findNavController().navigate(action)
        })
    }

    private fun initEditBtn() {
        binding.root.findViewById<View>(R.id.fab_edit)?.setOnClickListener {
            val action = DetailTaskFragmentDirections
                .actionDetailTaskDestToAddTaskFragmentDest(args.taskId)
            findNavController().navigate(action)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                viewModel.deleteTask(args.taskId)
                return true
            }
        }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_task_fragment_menu, menu)
    }
}