package com.example.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.todoapp.FakeRepository
import com.example.todoapp.MainCoroutineRule
import com.example.todoapp.domain.GetTasksUseCase
import com.example.todoapp.domain.UpdateCompleteUseCase
import com.example.todoapp.entity.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class TasksViewModelTest {

    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var tasksRepository: FakeRepository

    private lateinit var getTasksUseCase: GetTasksUseCase
    private lateinit var updateCompleteUseCase: UpdateCompleteUseCase

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        tasksRepository = FakeRepository()
        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", true)
        val task3 = Task("Title3", "Description3", true)
        tasksRepository.addTasks(task1, task2, task3)

        getTasksUseCase = GetTasksUseCase(tasksRepository)
        updateCompleteUseCase = UpdateCompleteUseCase(tasksRepository)

        tasksViewModel = TasksViewModel(getTasksUseCase, updateCompleteUseCase)
    }

    @Test
    fun loadTaskAllFromRepository_loadingTogglesAndDataLoaded() {
        mainCoroutineRule.pauseDispatcher()

        tasksViewModel.refresh()

        assert(tasksViewModel.dataLoading.value == true)

        mainCoroutineRule.resumeDispatcher()

        assert(tasksViewModel.dataLoading.value == false)

        assert(tasksViewModel.taskList.value?.size == 3)
    }

    @Test
    fun loadTaskAll_returnError() {
        tasksRepository.shouldReturnError = true

        tasksViewModel.refresh()

        assert(tasksViewModel.dataLoading.value == false)

        assert(tasksViewModel.taskList.value.isNullOrEmpty())

        val snackBarMessage = "Tasks 정보를 불러오지 못했습니다."
        assert(tasksViewModel.snackbarText.value?.content == snackBarMessage)
    }
}