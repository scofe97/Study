package com.example.baekjoon.step_300_math

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val number = br.readLine().toInt()

    bw.write(math(number).toString())
    bw.flush()
    bw.close()
    br.close()
}

fun math(a: Int) : Int{
    var count = 0
    var temp = a

    while(temp >= 5){
        count += temp/5
        temp /= 5
    }

    return count
}
