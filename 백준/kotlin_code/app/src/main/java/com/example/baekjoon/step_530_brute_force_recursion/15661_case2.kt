package com.example.baekjoon.step_530_brute_force_recursion

import kotlin.math.abs
import kotlin.math.min

private var n = 0
private lateinit var arr: Array<IntArray>
private var res = Int.MAX_VALUE

fun main() {

    n = readLine()!!.trim().toInt()
    arr = Array(n) { readLine()!!.trim().split(" ").map { it.toInt() }.toIntArray() }

    for (i in 0 until n) {
        for (j in i + 1 until n) {
            arr[i][j] += arr[j][i]  //[1][2]. [2][1] + [3][1] + [4][1]...
        }
    }

    backtracking(0, 0, BooleanArray(n))
    println(res)
}


private fun backtracking(depth: Int, x: Int, team: BooleanArray) {

    // 8팀이면 4팀까지 구함
    if (depth > n/2) return

    // 팀이 구성됨
    if (depth in 2..n/2) {
        res = min(res, getTeamScore(team, depth))
    }

    // 순환
    for (i in x until n) {
        team[i] = true
        backtracking(depth + 1, i + 1, team)
        team[i] = false
    }
}

private fun getTeamScore(team: BooleanArray, depth: Int): Int {
    val team1 = IntArray(depth)
    val team2 = IntArray(n - depth)
    var p1 = 0
    var p2 = 0
    for ((i, b) in team.withIndex()) {
        if (b) {
            team1[p1++] = i
        } else {
            team2[p2++] = i
        }
    }

    var sum1 = 0
    for (i in team1.indices) {
        for (j in i + 1 until team1.size) {
            sum1 += arr[team1[i]][team1[j]]
        }
    }
    var sum2 = 0
    for (i in team2.indices) {
        for (j in i + 1 until team2.size) {
            sum2 += arr[team2[i]][team2[j]]
        }
    }
    return abs(sum1 - sum2)
}