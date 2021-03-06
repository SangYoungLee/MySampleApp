package com.syapp.todo.base

import com.syapp.todo.DaggerTestApplicationRule
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class BaseFragmentTest {

    @get:Rule
    val rule = DaggerTestApplicationRule()

    @Before
    abstract fun setUp()

    @After
    abstract fun tearDown()
}