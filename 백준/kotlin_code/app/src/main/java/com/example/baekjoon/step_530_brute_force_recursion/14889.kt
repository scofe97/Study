package com.example.baekjoon.step_530_brute_force_recursion

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs
import kotlin.math.min

// N은 무조건 짝수 절반씩 나눔( start, link )
// 특정사람과 같이하면 더해지는 능력치가 존재함
// 두 팀의 능력치 차이를 최소로 하려함

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val n = br.readLine().toInt()
private val list = Array<IntArray>(n) { IntArray(n) }
private val visited = BooleanArray(n)
private var result = Int.MAX_VALUE


fun main() {

    repeat(n) { idx ->
        list[idx] = br.readLine().split(" ").map { it -> it.toInt() }.toIntArray()
    }
    dfs(0,0)

    bw.write("$result")
    bw.flush()
    bw.close()
    br.close()
}

private fun dfs(index: Int, depth: Int){

    if(depth == n/2){
        var startTeam = 0
        var linkTeam = 0

        for(i in 0 until n-1){
            for(j in i+1 until n){

                if(visited[i] && visited[j]){
                    startTeam += list[i][j] + list[j][i]
                }else if (!visited[i] && !visited[j]) {
                    linkTeam += list[i][j] + list[j][i]
                }
            }
        }
        result = min(result, abs(startTeam-linkTeam))
        return
    }

    for(i in index until n){
        if(!visited[i]){
            visited[i] = true
            dfs(i+1, depth+1)
            visited[i] = false
        }
    }
}