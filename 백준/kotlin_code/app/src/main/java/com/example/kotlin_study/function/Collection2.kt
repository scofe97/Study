package com.example.kotlin_study.function

fun main(){

    // filter
    // Collection 에서 사용이 가능함
    // 조건에 맞는 값만 뽑을 수 있음

    val numbers = listOf(1, -2, 3, -4, 5, -6)
    val positives = numbers.filter { x -> x > 0 }
    val negatives = numbers.filter { it < 0 }

    println("positives : $positives")
    println("negatives : $negatives")



    // map
    // Collection 에서 사용이 가능함
    // Collection 의 모든 변수를 수정 할 수 있음

    val doubled = numbers.map { x -> x * 2 }
    val tripled = numbers.map { it * 3 }

    println("doubled : $doubled")
    println("tripled : $tripled")



    // any 함수
    // Boolean 값을 반환하며 람다함수로 조건을 건다.
    // 하나라도 만족하면 true, 아니면 false

    val anyNegative = numbers.any { it < 0 }
    val anyGT6 = numbers.any { it > 6 }

    println("anyNegative : $anyNegative")
    println("anyGT6 : $anyGT6")



    // all 함수
    // Boolean 값을 반환하며 람다함수로 조건을 건다.
    // 모두 만족하면 true, 아니면 false

    val allEven = numbers.all { it % 2 == 0}
    val allLess6 = numbers.all { it < 6}

    println("allEven : $allEven")
    println("allLess6 : $allLess6")



    // none 함수
    // Boolean 값을 반환하며 람다함수로 조건을 건다.
    // 모두 만족하지않으면 true, 아니면 false

    val allEven2 = numbers.none { it % 2 == 1}
    val allLess62 = numbers.none { it > 6}

    println("allEven2 : $allEven2")
    println("allLess62 : $allLess62")
}