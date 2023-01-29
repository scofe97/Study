package com.example.baekjoon.step_530_brute_force_recursion

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val n = br.readLine().toInt()

private val graph = IntArray(n + 1)
private val visited = BooleanArray(10)
private val list = br.readLine().split(" ")

private val sb= StringBuilder()
private var max = 0L
private var min = Long.MAX_VALUE
private var result = Array<String>(2){""}

fun main() {

    dfs(0)

    bw.write("${result[0]}\n")
    bw.write("${result[1]}\n")
    bw.flush()
    bw.close()
    br.close()
}

private fun dfs(step: Int) {
    if (step == n + 1) {

        for(i in graph){
            sb.append(i)
        }
        if(max < sb.toString().toLong()){
            max = sb.toString().toLong()
            result[0] = sb.toString()
        }
        if(min > sb.toString().toLong()){
            min = sb.toString().toLong()
            result[1] = sb.toString()
        }
        sb.clear()
        return
    }

    for (i in 0 until 10) {
        if (!visited[i]) {
            if (step == 0) {
                visited[i] = true
                graph[step] = i
                dfs(step+1)
                visited[i] = false
            } else {
                when (list[step - 1]) {
                    "<" -> {
                        if (graph[step - 1] < i) {
                            visited[i] = true
                            graph[step] = i
                            dfs(step+1)
                            visited[i] = false
                        }
                    }
                    ">" -> {
                        if (graph[step - 1] > i) {
                            visited[i] = true
                            graph[step] = i
                            dfs(step+1)
                            visited[i] = false
                        }
                    }
                }
            }

        }
    }
}