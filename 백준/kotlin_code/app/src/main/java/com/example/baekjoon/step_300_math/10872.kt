package com.example.baekjoon.step_300_math

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val number = br.readLine().toInt()
    bw.write(factorial(number).toString())
    bw.flush()
    bw.close()
    br.close()
}

fun factorial(a: Int) : Int{
    return if(a == 0 )  1
    else a * factorial(a-1)
}
