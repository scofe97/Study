package com.example.baekjoon.step_510_brute_force_back_tracking

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    val space = br.readLine().toInt()
    val visit = Array<Int>(space){0}
    var count = 0

    fun check(level: Int) : Boolean{
        for(i in 0 until level)
            if(visit[i] == visit[level] || abs(visit[level] - visit[i]) == level - i) return false
        // 같은 행 , 대각선에 있으면 안된다
        // 대각선 ( 현재위치에서 x,y 축을 둘다 +- 1씩 한값)
        // x,y 축의 차가 현재위치의 x,y축의 차와 같으면 대각선임
        return true
    }

    fun dfs9663(step:Int, space:Int){
        if(step == space) count++
        else{
            for(i in 0 until space){
                visit[step] = i
                if(check(step)) dfs9663(step+1, space)
            }
        }
    }

    dfs9663(0, space)

    bw.write("$count")
    bw.flush()
    bw.close()
    br.close()

}

