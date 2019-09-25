package com.example.mysampleapp.base

import com.example.mysampleapp.DaggerTestApplicationRule
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