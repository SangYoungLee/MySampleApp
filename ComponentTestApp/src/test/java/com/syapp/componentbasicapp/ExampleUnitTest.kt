package com.syapp.componentbasicapp

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private lateinit var mObj: TestObj

    @Test
    fun testLateInitVar() {
        mObj = TestObj(0)

        mObj = TestObj(2)
    }
}

data class TestObj(private val number: Int)
