package com.example.baekjoon.step_520_brute_force_permutation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val sb = StringBuilder()
private val sb2 = StringBuilder()

// 원래 풀려던 방식
// 메모리초과 나옴
fun main() {

    while(true){

        val st = StringTokenizer(br.readLine())

        val count = st.nextToken().toInt()
        if(count == 0 ) break

        val list = IntArray(count)
        val temp = MutableList(6){0}
        val visited = BooleanArray(count)
        val strSet = mutableSetOf<String>()

        repeat(count){ idx->
            list[idx] = st.nextToken().toInt()
        }

        fun dfs(step:Int){
            sb2.clear()

            if(step == 6){
                val t = temp.sorted()
                for(i in t){
                    sb2.append("$i ")
                }
                if(!strSet.contains(sb2.toString())){
                    strSet.add(sb2.toString())
                    sb.append(sb2.toString()).append("\n")
                }
                return
            }

            for(i in 0 until count){
                if(!visited[i]){
                    visited[i] = true
                    temp[step] = list[i]
                    dfs( step+1)
                    visited[i] = false
                }

            }
        }

        dfs(0)
        bw.write(sb.toString())
        sb.clear()
        bw.write("\n")
    }

    bw.flush()
    bw.close()
    br.close()
}

