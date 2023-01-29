package com.example.kotlin_study.kotlin_basic


// 코틀린 함수
fun main(){

    // for loop
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }


    //// Range
    // in -> 범위안에 있는가
    val x = 10
    val y = 9
    if ( x in 1..y){
        println("fits in range")
    }

    val list = listOf("a", "b", "c", "d")

    // 2 -> 가장 마지막 index
    println(list.lastIndex)
    // 0..3 -> index 최초값 ~ index 마지막 값
    println(list.indices)


    // !in -> 포함되지 않는다
    if(-1 !in 0..list.lastIndex){
        println("-1 is out of range")
    }
    if(list.size !in list.indices){
        println("list size is out of valid last indices range, too")
    }

    for (x in 1..10 step 2) {
        print(x)
    }

    // Collection
    // iterate over a collection

    for (item in items){
        println(item)
    }

    // items = listOf("apple", "banana", "kiwifruit")
    // orange 는 없으므로  apple 만 나옴
    when{
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }

    // 람다표현 filter 와 map 사용
    // a로 시작하는 단어를 대무낮로 모두 바꾸고 출력한다
    val fruits = listOf<String>("banana", "avocado", "apple", "kiwifruit")
    fruits
        .filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.uppercase() }
        .forEach{ println(it) }
}