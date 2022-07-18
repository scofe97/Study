package com.example.kotlin_study

data class Person(var name: String, var age: Int)

fun main(){

    val person = Person("",0)
    person.name = "Simbo"
    person.age = 56
    println("$person")

    // let
    // 자기 자신을 받아서 반환하는 형식
    val result = person.let{
        it.name = "Jamges"
        it.age = 56
        it
    }

    val resultStr = person.let{
        it.name = "Steve"
        it.age = 56
        "${it.name} is ${it.age}"
    }

    println("$result")
    println("$resultStr")


    // with
    // with(~)  -> ~ 안의 요소들을 자유롭게 사용

    with(person){
        println(name)
        println(age)
    }


    // run
    // with 처럼 인자로 람다 리시버 받고 T.run 의 형태인데
    // T가 non-null 일때만 사용이 가능하다

    val ageNextYear = person.run {
        ++age
    }

    println("$ageNextYear")


    // apply
    // with 와 동일하게 객체에 접근 -> 자기자신을 반환함

    val result2 = person.apply {
        name = "James"
        age = 56
    }


    val state = State.RUNNING
    val message = when(state){
        State.IDLE -> "It's idle"
        State.RUNNING -> "It's running"
        State.FINISHED -> "It's finished"
    }
    println(message)
    println(State.valueOf("RUNNING"))

    for(state in State.values()){
        println("${state.name} == ${state.ordinal}")
    }

    println(Animal.LION.containsLion())
    println(Animal.TIGER.containsLion())

    for(animal in Animal.values()){
        println("${animal.name} == ${animal.animal}")
    }

    val mammal : Mammal = Cat("타이")
    when(mammal){
        is Cat -> println("안녕 고양이 ${mammal.name}")
        is Dog -> println("안녕 강아지 ${mammal.name}")
        is Human -> println("안녕 ${mammal.name}")
    }

}

enum class State {
    IDLE, RUNNING, FINISHED                           // 1
}

enum class Animal (val animal : String) {
    LION("사자"),
    TIGER("호랑이"),
    DOG("개");

    fun containsLion() = (this.animal == "사자")
}

sealed class Mammal(val name : String)
class Cat(val catName: String) : Mammal(catName)
class Dog(val dogName:String) : Mammal(dogName)
class Human(val humanName:String, val job: String) : Mammal(humanName)