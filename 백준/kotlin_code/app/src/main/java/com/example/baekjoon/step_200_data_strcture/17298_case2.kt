package com.example.baekjoon.step_200_data_strcture

import java.io.*
import java.util.*


fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val stack = Stack<Int>() // 스택

    var N = br.readLine().toInt() // N입력
    val st = StringTokenizer(br.readLine())
    val nums = br.readLine().split(" ").toMutableList()

    for (i in 0 until nums.size) {
        while (!stack.empty() && nums[stack.peek()].toInt() < nums[i].toInt()) {
            nums[stack.pop()] = nums[i]
        }
        stack.push(i)
    }
    while (!stack.empty()) {
        nums[stack.pop()] = "-1"
    }
    for (i in 0 until nums.size) {
        bw.write("${nums[i]} ")
    }
    bw.flush()
    bw.close()
}