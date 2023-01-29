package com.example.baekjoon.basic

import java.util.*
import kotlin.collections.LinkedHashMap

fun main(){

    val graph = LinkedHashMap<Char, LinkedList<Char>>()

    graph.run {
        set('A', LinkedList())
        get('A')?.run {
            add('B')
            add('D')
        }
        set('B', LinkedList())
        get('B')?.run {
            add('A')
            add('C')
        }

        set('C', LinkedList())
        get('C')?.run {
            add('B')
        }
        set('D', LinkedList())
        get('D')?.run {
            add('A')
            add('E')
        }
        set('E', LinkedList())
        get('E')?.run {
            add('B')
            add('D')
        }
    }

    println(graph)
}