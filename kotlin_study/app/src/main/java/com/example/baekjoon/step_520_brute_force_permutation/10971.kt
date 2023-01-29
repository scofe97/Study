package com.example.baekjoon.step_520_brute_force_permutation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val count = br.readLine().toInt()
private val arr = Array(count){IntArray(count)}
private val visited = BooleanArray(count){false}

private var min = Integer.MAX_VALUE

fun main(){

    repeat(count){ idx->
        arr[idx] = br.readLine().split(" ").map { it -> it.toInt() }.toIntArray()
    }

    for(i in 0 until count){
        dfs(i,i,0,0)
    }

    bw.write(min.toString())
    bw.flush()
    bw.close()
    br.close()
}

private fun dfs(start:Int, vertex:Int, step: Int, sum:Int){
    visited[vertex] = true

    if(step == count-1 && arr[vertex][start]>0){
        min = min(min, sum + arr[vertex][start])
    }

    for(i in 0 until count){
        if(!visited[i] && arr[vertex][i] != 0){
            dfs(start,i,step+1, sum+arr[vertex][i])
        }
    }
    visited[vertex] = false
}