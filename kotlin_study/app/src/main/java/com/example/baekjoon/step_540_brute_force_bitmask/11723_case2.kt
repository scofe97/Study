package com.example.baekjoon.step_540_brute_force_bitmask

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val count = br.readLine().toInt()
private val list = mutableListOf<Int>()

private var s = 0

fun main(){

    repeat(count){
        val st = StringTokenizer(br.readLine())
        val c = st.nextToken()
        var n = 0
        if(st.hasMoreTokens()) n = st.nextToken().toInt()

        // println("s의 값 $s")
        when(c){
            "add" ->{
                s = s or ((1 shl n))

            }
            "remove" ->{
                s = s and (1 shl n).inv()
            }

            "check" ->{
                if(s and ((1 shl n)) == 0) bw.write("0\n") else bw.write("1\n")
            }

            "toggle" ->{
                s = s xor (1 shl n)
            }

            "all" ->{
                s = (1 shl 21) -1
            }

            "empty" ->{
                s = 0
            }

        }
    }
    bw.flush()
    bw.close()
    br.close()
}