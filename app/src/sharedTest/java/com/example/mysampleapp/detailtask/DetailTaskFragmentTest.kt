package com.example.mysampleapp.detailtask

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.mysampleapp.R
import com.example.mysampleapp.base.BaseFragmentTest
import com.example.mysampleapp.entity.Task
import com.example.mysampleapp.repository.ITaskRepository
import com.example.mysampleapp.util.deleteAllRunBlocking
import com.example.mysampleapp.util.saveTaskBlocking
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class DetailTaskFragmentTest : BaseFragmentTest() {

    private lateinit var repository: ITaskRepository

    override fun setUp() {
        repository = rule.component.taskRepository
        repository.deleteAllRunBlocking()
    }

    override fun tearDown() {

    }

    @Test
    fun activeTaskDetails_DisplayedInUi() {
        val activeTask = Task("Active Task", "AndroidX Rocks", false)
        repository.saveTaskBlocking(activeTask)

        // WHEN - Details fragment launched to display task
        val bundle = DetailTaskFragmentArgs(activeTask.id).toBundle()
        launchFragmentInContainer<DetailTaskFragment>(bundle, R.style.AppTheme)

        // THEN - Task details are displayed on the screen
        // make sure that the title/description are both shown and correct

    }
}