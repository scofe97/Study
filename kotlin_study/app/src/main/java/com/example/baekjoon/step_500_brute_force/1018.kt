package com.example.baekjoon.step_500_brute_force

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.min

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    val st = StringTokenizer(br.readLine())
    var column = st.nextToken().toInt()
    val row = st.nextToken().toInt()

    val list = MutableList<List<String>>(column) { emptyList() }
    val list2 = MutableList(column) { MutableList(row) { 0 } }

    repeat(column) { idx ->
        val line = StringTokenizer(br.readLine())
        list[idx] = line.nextToken().chunked(1)
    }

    var result = Integer.MAX_VALUE

    for (i in 0 .. (column - 8)) {
        for (j in 0 .. (row - 8)) {
            var tempB = 0
            var tempW = 0

            for (c in i until i + 8) {
                for (r in j until j + 8) {
                    repeat(2){ idx ->
                        list2[c][r] = 0

                        if(idx == 0 ){
                            if((i+j) % 2 == 0){
                                if((c+r) % 2 == 0 && list[c][r] != "B") list2[c][r] = 1
                                if((c+r) % 2 == 1 && list[c][r] == "B") list2[c][r] = 1
                            }else{
                                if((c+r) % 2 == 0 && list[c][r] == "B") list2[c][r] = 1
                                if((c+r) % 2 == 1 && list[c][r] != "B") list2[c][r] = 1
                            }
                            tempB += list2[c][r]

                        }else{
                            if((i+j) % 2 == 0){
                                if((c+r) % 2 == 0 && list[c][r] != "W") list2[c][r] = 1
                                if((c+r) % 2 == 1 && list[c][r] == "W") list2[c][r] = 1
                            }else{
                                if((c+r) % 2 == 0 && list[c][r] == "W") list2[c][r] = 1
                                if((c+r) % 2 == 1 && list[c][r] != "W") list2[c][r] = 1
                            }
                            tempW += list2[c][r]

                        }

                    }
                }
            }

            result = min(result, min(tempB, tempW))
        }
    }
    bw.write("$result")
    bw.close()
    br.close()

}