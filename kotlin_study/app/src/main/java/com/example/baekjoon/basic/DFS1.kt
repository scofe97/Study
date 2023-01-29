package com.example.baekjoon.basic

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))

private val st = StringTokenizer(br.readLine())
private var n = st.nextToken().toInt()
private var m : Int = st.nextToken().toInt()
private var list = br.readLine().split(" ").map{it -> it.toInt()}.toIntArray()
private var visited = BooleanArray(n)
private val arr = IntArray(m)

private fun dfs(index:Int, step: Int){
    if (step == m) {
        // 출력
        for (i in arr) {
            bw.write("$i ")
        }
        bw.write("\n")

    } else {
        for (i in index until n) {
            if(!visited[i]){
                visited[i] = true
                arr[step] = list[i]
                dfs(i+1, step + 1)
                visited[i] = false
            }
        }
    }
}

private fun main(){

    dfs(0,0)

    bw.flush()
    bw.close()
    br.close()

}
