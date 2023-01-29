package com.example.baekjoon.step_540_brute_force_bitmask

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs
import kotlin.math.min

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val count = br.readLine().toInt()
private val list = Array<IntArray>(count){ IntArray(count) }
private var result = Integer.MAX_VALUE

private fun main(){

    repeat(count){ idx ->
        val temp = br.readLine().split(" ").map{it -> it.toInt()}.toIntArray()
        list[idx] = temp
    }

    for(i in 1 until (1 shl count)){
        var start = 0
        val startTeam = BooleanArray(count)

        for(j in 0 until count){
            if(i and (1 shl j) != 0) {
                start++
                startTeam[j] = true
            }
        }
        if(start == count/2){
            var startSum = 0
            var linkSum = 0

            for( r in 0 until count){
                for( c in 0 until count){
                    if(startTeam[r] && startTeam[c]){
                        startSum += list[r][c]
                    }else if(!startTeam[r] && !startTeam[c]){
                        linkSum += list[r][c]
                    }
                }
            }
            result = min(result, abs(startSum-linkSum))
        }
    }

    bw.write("$result")
    bw.flush()
    bw.close()
    br.close()
}
