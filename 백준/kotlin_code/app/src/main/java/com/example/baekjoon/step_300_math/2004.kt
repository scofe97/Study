package com.example.baekjoon.step_300_math

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.min


fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val st = StringTokenizer(br.readLine())
    val num1 = st.nextToken().toInt()
    val num2 = st.nextToken().toInt()

    bw.write(math2(num1, num2).toString())
    bw.flush()
    bw.close()
    br.close()
}

fun math2(a: Int, b:Int) : Int{
    var countFive = 0
    var countTwo = 0

    countFive = fivePowerN(a) - fivePowerN(b) - fivePowerN(a-b)
    countTwo = twoPowerN(a) - twoPowerN(b) - twoPowerN(a-b)

    return min(countFive, countTwo)
}

fun fivePowerN(num: Int) : Int{
    var count = 0
    var temp = num

    while(temp >= 2){
        count += temp/2
        temp /= 2
    }
    return count
}

fun twoPowerN(num: Int) : Int{
    var count = 0
    var temp = num

    while(temp >= 5){
        count += temp/5
        temp /= 5
    }
    return count
}