package com.syapp.todo

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.syapp.todo.application.TodoApplication

class CustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TodoApplication::class.java.name, context)
    }
}