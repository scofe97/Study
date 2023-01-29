package com.example.baekjoon.step_200_data_strcture

class ArrayListQueue<T> : Queue<T> {

    private val list = arrayListOf<T>()

    override fun push(element: T) {
        list.add(element)
    }


    override fun pop(): T? {
        if(list.isNotEmpty()){
            val temp = list[0]
            list.removeAt(0)
            return temp
        }
        return null
    }

    override fun front(): T? = if(list.isNotEmpty()) list[0] else null

    override fun back(): T? = if(list.isNotEmpty()) list.last() else null

    override fun empty(): Int = if(list.isEmpty()) 1 else 0

    fun size(): Int = list.size
}