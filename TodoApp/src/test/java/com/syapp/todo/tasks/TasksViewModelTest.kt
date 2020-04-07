package com.syapp.todo.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.syapp.todo.MainCoroutineRule
import com.syapp.todo.base.data.Result
import com.syapp.todo.domain.GetTasksUseCase
import com.syapp.todo.domain.UpdateCompleteUseCase
import com.syapp.todo.entity.Task
import com.syapp.todo.repository.ITaskRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class TasksViewModelTest {

    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var tasksRepository: ITaskRepository

    private lateinit var getTasksUseCase: GetTasksUseCase
    private lateinit var updateCompleteUseCase: UpdateCompleteUseCase

    private lateinit var tasks: List<Task>

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        tasksRepository = mock(ITaskRepository::class.java)
        tasks = arrayListOf(Task("Title1", "Description1"),
            Task("Title2", "Description2", true),
            Task("Title3", "Description3", true))

        getTasksUseCase = GetTasksUseCase(tasksRepository)
        updateCompleteUseCase = UpdateCompleteUseCase(tasksRepository)

        tasksViewModel = TasksViewModel(getTasksUseCase, updateCompleteUseCase)
    }

    @Test
    fun loadTaskAllFromRepository_loadingTogglesAndDataLoaded() {
        // given
        reset(tasksRepository)

        runBlocking {
            `when`(tasksRepository.getTasks())
                .thenReturn(Result.Success(tasks))
        }

        // when
        mainCoroutineRule.pauseDispatcher()

        tasksViewModel.refresh()

        // then
        assert(tasksViewModel.dataLoading.value == true)


        // when
        mainCoroutineRule.resumeDispatcher()

        // then
        assert(tasksViewModel.dataLoading.value == false)

        assert(tasksViewModel.taskList.value?.size == 3)
    }

    @Test
    fun loadTaskAll_returnError() {
        // given
        reset(tasksRepository)

        runBlocking {
            `when`(tasksRepository.getTasks())
                .thenReturn(Result.Failure(Exception("get Task Fail")))
        }

        // when
        tasksViewModel.refresh()

        // then
        assert(tasksViewModel.dataLoading.value == false)

        assert(tasksViewModel.taskList.value.isNullOrEmpty())

        val snackBarMessage = "Tasks 정보를 불러오지 못했습니다."
        assert(tasksViewModel.snackbarText.value?.content == snackBarMessage)
    }
}