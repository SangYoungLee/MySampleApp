package com.syapp.todo

import androidx.test.core.app.ApplicationProvider
import com.syapp.todo.application.TodoApplication
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class DaggerTestApplicationRule : TestWatcher() {

    lateinit var component: TestAppComponent

    override fun starting(description: Description?) {
        super.starting(description)

        val app = ApplicationProvider.getApplicationContext<TodoApplication>()
        component = DaggerTestAppComponent.factory().create(app)
        component.inject(app)
    }
}