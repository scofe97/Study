package com.example.kotlin_study.function

fun main() {

    // find, findLast
    // startsWith -> ~로 시작하는 값
    // find -> 시작부터 찾음
    // findLast -> 마지막에서 찾음
    // 못찾으면 null 값
    val words = listOf("Lets", "find", "something", "in", "collection", "somehow")

    val first = words.find { it.startsWith("some") }
    val last = words.findLast { it.startsWith("some") }

    val nothing = words.find { it.contains("nothing") }

    println("first : $first")
    println("last : $last")
    println("nothing : $nothing")


    // first, last
    // 처음과 마지막 값을 가져오는 함수
    // 단 람다값으로 조건에 맞는 최초와 마지막 값을 가져올 수 있음
    val numbers = listOf(1, -2, 3, -4, 5, -6)

    val first2 = numbers.first()
    val last2 = numbers.last()

    val firstEven = numbers.first { it % 2 == 0 }
    val lastOdd = numbers.last { it % 2 != 0 }

    println("first2 : $first2")
    println("last2 : $last2")
    println("firstEven : $firstEven")
    println("lastOdd : $lastOdd")


    // firstOrNull, lastOrNull
    // 처음과 마지막 값을 가져오는 함수
    // 단 람다값으로 조건에 맞지않으면 null 값 반환

    val words2 = listOf("foo", "bar", "baz", "faz")         // 1
    val empty = emptyList<String>()                        // 2

    val first3 = empty.firstOrNull()                        // 3
    val last3 = empty.lastOrNull()                          // 4

    val firstF = words2.firstOrNull { it.startsWith('f') }  // 5
    val firstZ = words2.firstOrNull { it.startsWith('z') }  // 6
    val lastF = words2.lastOrNull { it.endsWith('f') }      // 7
    val lastZ = words2.lastOrNull { it.endsWith('z') }      // 8

    println("first3 : $first3")
    println("last3 : $last3")

    println("firstF : $firstF")
    println("firstZ : $firstZ")
    println("lastF : $lastF")
    println("lastZ : $lastZ")


    // count
    // 요소의 개수를 출력
    // 람다로 조건에 맞는 개수 출력 가능

    val numbers2 = listOf(1, -2, 3, -4, 5, -6)

    val totalCount = numbers2.count()
    val evenCount = numbers2.count { it % 2 == 0 }

    println("totalCount : $totalCount")
    println("evenCount : $evenCount")

}