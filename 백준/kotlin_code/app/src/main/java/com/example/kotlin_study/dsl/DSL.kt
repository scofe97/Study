package com.example.kotlin_study.dsl

data class Person(
    var name: String? = null,
    var age: Int? = null,
)

fun makePerson(makePersonAction: (Person)-> Unit): Person {
    val person = Person()
    makePersonAction(person)
    return person
}


fun main() {
    println(makePerson{
        it.age = 10
        it.name = "KOTLIN"
    })
}


