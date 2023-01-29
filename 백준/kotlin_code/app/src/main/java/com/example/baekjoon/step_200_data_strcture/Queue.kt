package com.example.baekjoon.step_200_data_strcture

interface Queue<T> {

    fun push(element: T)

    fun pop(): T?

    fun front(): T?

    fun back(): T?

    fun empty(): Int
}