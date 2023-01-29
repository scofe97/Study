package com.example.baekjoon.step_510_brute_force_back_tracking

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))

fun main(){

    var st = StringTokenizer(br.readLine())
    val count = st.nextToken().toInt()
    val space = st.nextToken().toInt()
    val sb = StringBuilder()
    val sb2 = StringBuilder()
    val set = mutableSetOf<String>()


    val graph = IntArray(space)
    val arr = IntArray(count)
    val visit = BooleanArray(count){false}

    st = StringTokenizer(br.readLine())
    repeat(count){ idx ->
        arr[idx] = st.nextToken().toInt()
    }

    arr.sort()

    fun dfs15664(depth:Int){

        if(depth == space) {
            sb2.clear()
            for(i in graph){
                sb2.append("$i ")
            }

            if(!set.contains(sb2.toString())){
                set.add(sb2.toString())
                sb.append(sb2.toString()).append("\n")
            }
            return
        }

        for(i in arr.indices){
            if(!visit[i] && depth==0){
                visit[i] = true
                graph[depth] = arr[i]
                dfs15664(depth+1)
                visit[i] = false

            }else if(!visit[i] && graph[depth-1] <= arr[i]){
                visit[i] = true
                graph[depth] = arr[i]
                dfs15664(depth+1)
                visit[i] = false
            }
        }

    }

    dfs15664(0)

    bw.write(sb.toString())
    bw.flush()
    bw.close()
    br.close()

}