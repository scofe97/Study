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

fun main(){

    repeat(count){
        val st = StringTokenizer(br.readLine())
        val c = st.nextToken()
        var n = 0
        if(st.hasMoreTokens()) n = st.nextToken().toInt()
        when(c){
            "add" ->{
                list.add(n)
            }

            "remove" ->{
                list.remove(n)
            }

            "check" ->{
                if(list.indexOf(n) != -1) bw.write("1\n") else bw.write("0\n")
            }

            "toggle" ->{
                if(list.indexOf(n) != -1) list.remove(n) else list.add(n)
            }

            "all" ->{
                list.clear()
                for(i in 0 until 20){
                    list.add(i+1)
                }
            }

            "empty" ->{
                list.clear()
            }

        }
    }

    bw.flush()
    bw.close()
    br.close()
}