package com.example.kotlin_study

import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
fun main(){

    val fruits = listOf<String>("apple", "banana", "kiwi", "peach")

    println("${fruits.size}")
    println(fruits[2])
    println(fruits.indexOf("peach"))

    val fruits2 = mutableListOf<String>("apple", "banana", "kiwi", "peach")
    fruits2.remove("apple")
    fruits2.add("grape")
    println("fruits: $fruits2")

    fruits2.addAll(listOf("melon", "cherry"))
    println("fruits: $fruits2")
    fruits2.removeAt(3)
    println("fruits: $fruits2")

    val numbers = setOf<Int>(33,22,11,1,22,3)
    println(numbers)
    println("numbers.size: ${numbers.size}")
    println("numbers.contains(1): ${numbers.contains(1)}")
    println("numbers.isEmpty(): ${numbers.isEmpty()}")

    val numbers2 = mutableSetOf<Int>(33, 22, 11, 1, 22, 3)
    println(numbers2)
    numbers2.add(100)
    numbers2.remove(33)
    println(numbers2)
    numbers2.removeIf { it < 10 }
    println(numbers2)



    val numbersMap = mapOf<String, String>("1" to "one", "2" to "two", "3" to "three")
    println("numbersMap: $numbersMap")

    val numbersMap2 = mapOf(Pair("1","one"),Pair("2","two"),Pair("3","three"))
    println("numbersMap2 : $numbersMap2")

    println("numbersMap.get(\"1\"): ${numbersMap.get("1")}")
    println("numbersMap[\"1\"]: ${numbersMap["1"]}")
    println("numbersMap[\"1\"]: ${numbersMap.values}")
    println("numbersMap keys:${numbersMap.keys}")
    println("numbersMap values:${numbersMap.values}")

    for (value in numbersMap.values) {
        println(value)
    }

    val numbersMap3 = mutableMapOf<String, String>(
        "1" to "one", "2" to "two", "3" to "three")
    println("numbersMap: $numbersMap3")

    numbersMap3.put("4", "four")
    numbersMap3["5"] = "five"
    println("numbersMap: $numbersMap3")

    numbersMap3.remove("1")
    println("numbersMap: $numbersMap3")

    numbersMap3.clear()
    println("numbersMap: $numbersMap3")

}