package com.example.baekjoon.step_540_brute_force_bitmask

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.max

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val st = StringTokenizer(br.readLine())
private val column = st.nextToken().toInt()
private val row = st.nextToken().toInt()
private val list = Array<IntArray>(column){ IntArray(row) }

private fun main(){

    repeat(column){ idx ->
        val temp = br.readLine().toString()
        for(i in 0 until row){
            list[idx][i] = temp[i].digitToInt()
        }
    }

    var ans = 0

    // 범위 숫자 (칸이 12개면 이진수 범위로 12개 숫자 나타냄) )
    for(s in 0 until(1 shl (column*row))){

        var sum = 0

        // column 기준 (
        for(i in 0 until column){
            var cur = 0

            // row 기준
            for(j in 0 until row){

                // column i 번째 j 행
                val k = i*row+j

                // <-> 기준으로 간다 반복되면 *10해서 더하고 아니면 그냥 더하고 초기화
                if((s and (1 shl k)) == 0){
                    cur = cur * 10 + list[i][j]
                }else{
                    sum += cur
                    cur = 0
                }
            }
            sum += cur
        }


        // row 기준 (
        for(i in 0 until row){
            var cur = 0

            // column 기준
            for(j in 0 until column){

                // row i 번째 j 열
                val k = j*row+i

                // △▽ 기준으로 간다 반복되면 *10해서 더하고 아니면 그냥 더하고 초기화
                if((s and (1 shl k)) != 0){
                    cur = cur * 10 + list[j][i]
                }else{
                    sum += cur
                    cur = 0
                }
            }
            sum += cur
        }

        ans = max(ans,sum)
    }

    bw.write("$ans")
    bw.flush()
    bw.close()
    br.close()

}

