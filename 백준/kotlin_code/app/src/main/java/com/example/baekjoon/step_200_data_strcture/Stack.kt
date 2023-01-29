package com.example.baekjoon.step_200_data_strcture

import java.util.ArrayList

class Stack() {

    private val stackArrayList: ArrayList<Int> = ArrayList<Int>()

    fun push(number: Int) = stackArrayList.add(number)

    fun pop() : Int {
        return if (stackArrayList.isEmpty()) {
            -1

        } else {
            val number = stackArrayList.last()
            stackArrayList.removeLast()
            number
        }
    }

    fun size(): Int = stackArrayList.size

    fun empty(): Int = if (stackArrayList.isEmpty()) 1 else 0

    fun top(): Int = if (stackArrayList.isEmpty()) -1 else stackArrayList.last()
}