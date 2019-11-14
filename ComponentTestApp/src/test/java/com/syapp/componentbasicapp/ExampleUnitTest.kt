package com.syapp.componentbasicapp

import org.junit.Test
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private lateinit var mObj: TestObj

    @Test
    fun testLateInitVar() {
    }

    @Test
    fun testMoshi() {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter = moshi.adapter(TestObj::class.java)

        val json = "{\"number\":123, \"str\":\"text\"}"
        val testObj = jsonAdapter.fromJson(json)
        println(testObj)
    }
}

data class TestObj(private val number: Int, private val str: String)
