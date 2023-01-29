package com.example.baekjoon.step_520_brute_force_permutation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.max

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val arr = IntArray(10002)

private fun main() {

    val count= br.readLine().toInt()
    val st = StringTokenizer(br.readLine())

    repeat(count){ idx->
        arr[idx] = st.nextToken().toInt()
    }

    for(i in count-1 downTo 0){

        if(i == 0){
            bw.write("-1")

        } else if(arr[i-1] > arr[i]){
            val temp = arr[i-1]
            var m = Integer.MIN_VALUE

            for(j in i until count ){
                if(arr[j] < temp) m = max(m, arr[j])
            }

            arr[arr.indexOf(m)] = temp
            arr[i-1] = m


            arr.sortDescending(i, count)

            for(t in 0 until count ){
                bw.write("${arr[t]} ")
            }
            break
        }

    }
    bw.flush()
    bw.close()
    br.close()

}

