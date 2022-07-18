package com.example.baekjoon.step_500_brute_force

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.max

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    val number = br.readLine().toInt()
    val list = MutableList(number) { MutableList(number) { "" } }
    val colorList = mutableListOf<String>("C", "P", "Z", "Y")

    repeat(number) { i ->
        val st = StringTokenizer(br.readLine())
        val temp = st.nextToken().chunked(1)
        repeat(number) { j ->
            list[i][j] = temp[j]
        }
    }

    val result = max(result(list, number, colorList), change(list, number, colorList))

    bw.write(result.toString())
    bw.flush()
    bw.close()
    br.close()
}


fun result(list: MutableList<MutableList<String>>, size: Int, colorList: MutableList<String>): Int {

    var resultR = 1
    var resultH = 1

    for (color in colorList) {

        for (i in 0 until size) {

            var tempR = 0
            var tempH = 0

            for (j in 0 until size) {

                if (list[i][j] == color) tempR++ else tempR = 0
                if (list[j][i] == color) tempH++ else tempH = 0

                if (tempR == size) return tempR
                if (tempH == size) return tempH

                resultR = max(resultR, tempR)
                resultH = max(resultH, tempH)

            }
        }
    }


    return max(resultH, resultR)
}

fun change(list: MutableList<MutableList<String>>, size: Int, colorList: MutableList<String>): Int {

    var result = 1

    for (i in 0 until size) {

        for (j in 0 until size - 1) {

            if (list[i][j] != list[i][j+1]) {
                val temp = list[i][j]
                list[i][j] = list[i][j + 1]
                list[i][j + 1] = temp

                val stepResult = result(list, size, colorList)
                result = max(result, stepResult)

                // println("$list  최대값 : $stepResult")

                list[i][j + 1] = list[i][j]
                list[i][j] = temp
                if (result == size) return result


            }

            if (list[j][i] != list[j+1][i]) {
                val temp = list[j][i]
                list[j][i] = list[j + 1][i]
                list[j + 1][i] = temp

                val stepResult = result(list, size, colorList)
                result = max(result, stepResult)

                // println("$list  최대값 : $stepResult")

                list[j + 1][i] = list[j][i]
                list[j][i] = temp

                if (result == size) return result

            }
        }

    }



    return result
}


