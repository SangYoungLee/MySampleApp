package com.example.mysampleapp.tasks

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.mysampleapp.R
import com.example.mysampleapp.activity.TasksActivity
import com.example.mysampleapp.base.BaseFragmentTest
import com.example.mysampleapp.entity.Task
import com.example.mysampleapp.repository.ITaskRepository
import com.example.mysampleapp.util.deleteAllRunBlocking
import com.example.mysampleapp.util.saveTaskBlocking
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.allOf
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created By lsy2014 on 2019-09-24
 */
@RunWith(AndroidJUnit4::class)
@MediumTest
class TasksFragmentTest : BaseFragmentTest() {

    private lateinit var repository: ITaskRepository

    override fun setUp() {
        repository = rule.component.taskRepository
        repository.deleteAllRunBlocking()
    }

    override fun tearDown() {

    }

    @Test
    fun display_title() {
        // GIVEN - One task already in the repository
        repository.saveTaskBlocking(Task("TITLE1", "DESCRIPTION1"))

        // WHEN - On startup
        launchActivity()

        runBlocking { delay(2000) }

        // THEN - Verify task is displayed on screen
        onView(withText("TITLE1"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkbox_click() {
        // GIVEN - One task already in the repository
        repository.saveTaskBlocking(Task("TITLE1", "DESCRIPTION1"))

        // WHEN - On startup & Click
        launchActivity()

        runBlocking { delay(1100) }

        onView(
            allOf(withId(R.id.checkbox), hasSibling(withText("TITLE1")))
        ).perform(
            click()
        )

        runBlocking { delay(2000) }

        // THEN - on Complete
        onView(
            allOf(withId(R.id.checkbox), hasSibling(withText("TITLE1")))
        ).check(
            matches(isDisplayed())
        )

        onView(
            allOf(withId(R.id.checkbox), hasSibling(withText("TITLE1")))
        ).check(
            matches(isChecked())
        )
    }

    private fun launchActivity(): ActivityScenario<TasksActivity>? {
        val activityScenario = ActivityScenario.launch(TasksActivity::class.java)
        activityScenario.onActivity { activity ->
            // Disable animations in RecyclerView
            (activity.findViewById(R.id.rv_tasks) as RecyclerView).itemAnimator = null
        }
        return activityScenario
    }
}