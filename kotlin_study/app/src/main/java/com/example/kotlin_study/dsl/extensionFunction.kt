package com.example.kotlin_study.kotlin_basic

fun main() {
    val testList = mutableListOf<Int>(1, 2, 3, 4, 5, 6, 7)

    // swap() 함수 호출
    testList.swap(0, 5)
    print(testList)

    // 매개 변수의 타입이 Shape인 함수에 Rectangle 타입의 인스턴스를 전달함
    printClassName(Rectangle())
}

open class Shape

// Rectangle = Shape 클래스를 상속하는 SubClass
class Rectangle: Shape()

// Shape 클래스의 확장 함수
fun Shape.getName() = "Shape"

// Rectangle 클래스의 확장 함수
fun Rectangle.getName() = "Rectangle"

fun printClassName(s: Shape) {
    println(s.getName())
}


fun MutableList<Int>.swap(index1: Int, index2: Int) {

    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}