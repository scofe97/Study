package com.example.baekjoon.step_530_brute_force_recursion

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))


fun main(){

    val st = StringTokenizer(br.readLine())
    val size = st.nextToken().toInt()
    val listSize = st.nextToken().toInt()
    val list = br.readLine().split(" ").sorted()
    val visited = BooleanArray(listSize)
    val graph = Array<String>(size){""}

    fun dfs(step: Int, start:Int){
        if(step == size){
            var temp1 = 0
            var temp2 = 0

            for(i in graph){
                when(i){
                    "a","e","i","o","u" -> temp1++
                    else -> temp2++
                }
            }

            if(temp1>=1 && temp2>=2){
                for(i in graph){
                    bw.write(i)
                }
                bw.write("\n")
            }
        }else{

            for(i in start until listSize){
                if(!visited[i]){
                    visited[i] = true
                    graph[step] = list[i]
                    dfs(step+1, i+1)
                    visited[i] = false
                }
            }
        }
    }

    dfs(0,0)

    bw.flush()
    bw.close()
    br.close()
}