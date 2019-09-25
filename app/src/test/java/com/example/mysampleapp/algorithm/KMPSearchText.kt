package com.example.mysampleapp.algorithm

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.ArrayList

/**
 * Created By lsy2014 on 2019-09-25
 */
@RunWith(JUnit4::class)
class KMPSearchText {

    @Test
    fun testKMPAlgorithm() {
        val str = "AAAAAABCCCFFF"
        val pattern = ""


    }

    private fun kmp(str: String, pattern: String): ArrayList<Int> {
        val list = arrayListOf<Int>()

        val pi = getPi(pattern)

        val sLength = str.length
        val pLength = pattern.length
        var j = 0

        val s = str.toCharArray()
        val p = pattern.toCharArray()

        // str - 전체 문자열, pattern - 찾을 문자열
        // j - 찾을 문자열의 비교 인덱스.
        // i - 전체 문자열과 비교할 인덱스이기 때문에 1씩 증가하기만 함. 절대 불규칙적으로 변경되지 않음.

        for (i in 0 until sLength) {
            while (j > 0 && s[i] != p[j]) {
                // 중간 단계 뛰어넘기.
                // pi 배열을 이용하여 j인 덱스를 변경시킴으로써 while 중단.
                j = pi[j - 1]
            }

            if (s[i] == p[j]) {
                if (j == pLength - 1) {
                    // j는 비교 인덱스로써, 인덱스가 찾을 문자열의 크기에 도달하면 문자열 찾음.
                    list.add(i - pLength + 1)
                    // 여러 개의 찾을 문자열이 있을 수 있기 때문.
                    j = pi[j]
                } else {
                    j++
                }
            }
        }


        return list
    }

    private fun getPi(pattern: String): IntArray {

        val length = pattern.length

        var j = 0

        val pi = IntArray(length)

        val p = pattern.toCharArray()



        for (i in 1 until length) {

            while (j > 0 && p[i] != p[j]) {

                j = pi[j - 1]

            }

            if (p[i] == p[j]) {

                pi[i] = ++j

            }

        }



        return pi

    }

//    https://mygumi.tistory.com/61
}