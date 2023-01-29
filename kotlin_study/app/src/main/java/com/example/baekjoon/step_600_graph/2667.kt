package com.example.baekjoon.step_600_graph

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val count = br.readLine().toInt()
private val list = Array<IntArray>(count) { IntArray(count) }
private val visited = Array<IntArray>(count) { IntArray(count) }

private val groupList = mutableListOf<Int>()
private var groupCount = 0

private fun main() {

    repeat(count) { idx ->
        val line = br.readLine()
        for (i in 0 until count) {
            list[idx][i] = line[i].digitToInt()
        }
    }
    dfs()
    groupList.sort()
    bw.write("${groupList.size}\n")
    for (i in groupList) {
        bw.write("$i \n")
    }

    bw.flush()
    bw.close()
    br.close()

}

private fun dfs() {

    for (i in 0 until count) {
        for (j in 0 until count) {
            if (list[i][j] == 1 && visited[i][j] == 0) {
                check(i, j, groupList.size + 1)
                groupList.add(groupCount)
                groupCount = 0
            }
        }
    }
}

private fun check(column: Int, row: Int, groupNumber: Int) {

    if (column !in 0 until count || row !in 0 until count) return

    if (list[column][row] == 0 || visited[column][row] != 0) return

    groupCount++
    visited[column][row] = count

    check(column + 1, row, groupNumber)
    check(column - 1, row, groupNumber)
    check(column, row + 1, groupNumber)
    check(column, row - 1, groupNumber)

}