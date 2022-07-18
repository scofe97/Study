package com.example.baekjoon.step_200_data_strcture

import java.util.*

class RingQueue<T>(listSize: Int)  {

    private val list = LinkedList<T>()
    private val size = listSize

    private var readIdx = 0
    private var writeIdx = 0

    fun push(element: T) {
        if(list.size == size){
            readIdx %= size
            list[readIdx] = element
            readIdx++
        }else{
            list.add(element)
            readIdx++
        }
    }

    fun pop(): T? {
        if(list.isNotEmpty()){
            writeIdx %= list.size
            return list.removeAt(writeIdx)
        }
        return null
    }

    fun pop(sequence: Int): T? {
        if(list.isNotEmpty()){
            writeIdx = (writeIdx + sequence - 1) % list.size
            return list.removeAt(writeIdx)
        }
        return null
    }

    fun empty() : Boolean = list.isEmpty()

}