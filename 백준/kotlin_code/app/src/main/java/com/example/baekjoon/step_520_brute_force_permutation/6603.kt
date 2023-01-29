package com.example.baekjoon.step_520_brute_force_permutation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))

fun main() {

    while(true){

        val st = StringTokenizer(br.readLine())

        val count = st.nextToken().toInt()
        if(count == 0 ) break

        val list = IntArray(count)
        val temp = IntArray(6)
        val visited = BooleanArray(count)

        repeat(count){ idx->
            list[idx] = st.nextToken().toInt()
        }

        fun dfs(start:Int, step:Int){
            if(step == 6){
                for(i in temp){
                    bw.write("$i ")
                }
                bw.write("\n")
                return
            }

            for(i in start until count){
                visited[i] = true
                temp[step] = list[i]
                dfs(i+1, step+1)
                visited[i] = true
            }
        }

        dfs(0,0)
        bw.write("\n")
    }

    bw.flush()
    bw.close()
    br.close()
}

