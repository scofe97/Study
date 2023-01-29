package com.example.baekjoon.step_200_data_strcture

class ArrayListQueue2<T> {

    private val list = arrayListOf<T>()

    fun pushFront(element: T) {
        list.add(0,element)
    }

    fun pushBack (element: T) {
        list.add(element)
    }


    fun popFront(): T? {
        return if(list.isNotEmpty()) list.removeFirst() else null
    }

    fun popBack(): T? {
        return if(list.isNotEmpty()) list.removeLast() else null
    }

    fun size(): Int = list.size

    fun empty(): Int = if(list.isEmpty()) 1 else 0

    fun front(): T? = if(list.isNotEmpty()) list.first() else null

    fun back(): T? = if(list.isNotEmpty()) list.last() else null

}

// 2 1

// 2 1