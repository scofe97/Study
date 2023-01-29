package com.example.baekjoon.step_200_data_strcture

import java.util.*
import kotlin.collections.ArrayList

fun main(){

    // 연결리스트(LinkedList) 데이터 검색 시간
    val linkedList: LinkedList<String> = LinkedList()
    linkedList.add("1")
    linkedList.addAll(arrayOf("2", "3"))
    linkedList.addFirst("Head")
    linkedList.addLast("Last")
    println(linkedList)

    var startTime = System.currentTimeMillis()
    for (i in 0 until 10000000) {
        linkedList.add(5,"$i")
        linkedList.removeAt(5)
    }
    println(linkedList)
    println("연결리스트(LinkedList) 데이터 삽입 소요 시간 : " + (System.currentTimeMillis() - startTime))
    println()

    // 연결리스트2(LinkedList) 데이터 검색 시간
    val linkedList2: LinkedList<String> = LinkedList()
    linkedList2.add("1")
    linkedList2.addAll(arrayOf("2", "3"))
    linkedList2.addFirst("Head")
    linkedList2.addLast("Last")
    println(linkedList2)

    val iterator = linkedList2.listIterator(4)

    startTime = System.currentTimeMillis()
    for (i in 0 until 10000000) {
        iterator.add("$i")
        iterator.previous()
        iterator.remove()
    }
    println(linkedList2)
    println("연결리스트2(LinkedList2) 데이터 삽입 소요 시간 : " + (System.currentTimeMillis() - startTime))
    println()




    // 선형리스트(ArrayList) 데이터 검색 시간
    val arrayList : ArrayList<String> = ArrayList()
    arrayList.add("1")
    arrayList.addAll(arrayOf("2","3"))
    arrayList.add(arrayList.size, "Last")
    println(arrayList)

    startTime = System.currentTimeMillis()
    for (i in 0 until 10000000) {
        arrayList[2]
    }
    println("선형리스트(ArrayList) 데이터 삽입 소요 시간 : " + (System.currentTimeMillis() - startTime))

}