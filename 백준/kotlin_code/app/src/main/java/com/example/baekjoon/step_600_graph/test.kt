package com.example.baekjoon.step_600_graph

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val count1 = br.readLine().toInt()
private val count2 = br.readLine().toInt()
private fun main(){

    bw.write("${count1+ count2}")
    bw.flush()
    bw.close()
    br.close()
}