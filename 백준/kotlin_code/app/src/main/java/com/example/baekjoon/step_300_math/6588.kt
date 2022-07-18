package com.example.baekjoon.step_300_math

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import kotlin.math.sqrt

const val maxN = 1000000

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val sb = StringBuilder()

    val nP = BooleanArray(maxN+1){true}
    val list = ArrayList<Int>()

    for(i in 2..sqrt(maxN.toDouble()).toInt() + 1) {
        if (!nP[i]) continue
        for (t in i * 2 until maxN+1 step i) nP[t] = false
    }

    for(i in 3..maxN){
        if(nP[i])  list.add(i)
    }

    nP[0] = false
    nP[1] = false
    nP[2] = false

    while(true) {
        val number = br.readLine().toInt()

        if (number == 0) break

        for (i in list) {

            if(i > number){
                sb.append("Goldbach's conjecture is wrong\n")
                break
            }

            if(nP[number - i]){
                sb.append("$number = $i + ${number - i} \n")
                break
            }

            if( i == list.last()) sb.append("Goldbach's conjecture is wrong\n")

        }
    }

    bw.write(sb.toString())
    bw.flush()
    bw.close()
    br.close()
}