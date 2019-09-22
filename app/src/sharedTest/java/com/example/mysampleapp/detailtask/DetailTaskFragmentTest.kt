package com.example.mysampleapp.detailtask

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.mysampleapp.R
import com.example.mysampleapp.base.BaseFragmentTest
import com.example.mysampleapp.entity.Task
import com.example.mysampleapp.repository.ITaskRepository
import com.example.mysampleapp.util.deleteAllRunBlocking
import com.example.mysampleapp.util.saveTaskBlocking
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
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

        runBlocking {
            delay(2000)
        }

        // THEN - Task details are displayed on the screen
        // make sure that the title/description are both shown and correct
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(matches(withText("Active Task")))
        onView(withId(R.id.tv_detail_content)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_content)).check(matches(withText("AndroidX Rocks")))
    }
}