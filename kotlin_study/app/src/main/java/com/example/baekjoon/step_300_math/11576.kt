package com.example.baekjoon.step_300_math

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.util.*
import kotlin.math.pow

fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val sb = StringBuilder()

    var st = StringTokenizer(br.readLine())

    val num1 = st.nextToken().toInt()
    val num2 = st.nextToken().toInt()

    var size = br.readLine().toInt()

    st = StringTokenizer(br.readLine())
    var sum = 0

    while(size != 0){
        sum += st.nextToken().toInt() * num1.toDouble().pow((size-1).toDouble()).toInt()
        size--
    }

    val result: Stack<Int> = Stack()
    while(sum != 0) {
        result.push(sum % num2)
        sum /= num2
    }


    while(result.isNotEmpty()) sb.append(result.pop()).append(" ")
    bw.write(sb.toString())
    bw.flush()
    bw.close()
    br.close()
}
