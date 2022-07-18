package com.example.baekjoon.step_500_brute_force

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    var list : MutableList<Int> = MutableList(9){0}
    var sum = 0

    repeat(9){ i ->
        val height = br.readLine().toInt()
        list[i] = height

        sum += height
    }

    list = result(list, sum)
    list.sort()

    for(i in list){
        if(i != 0 )bw.write("$i \n")
    }

    bw.flush()
    bw.close()
    br.close()
}

fun result(list: MutableList<Int>, sum: Int) : MutableList<Int>{
    for(i in 0 until 9){
        for(j in i+1 until 9){
            if(sum - (list[i] + list[j]) == 100){
                list[i] = 0
                list[j] = 0

                return list
            }
        }
    }

    return list
}
