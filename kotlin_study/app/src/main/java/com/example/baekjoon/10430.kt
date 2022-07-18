package com.example.baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun rest(a: Int, b: Int, c: Int) {

    println((a + b) % c)
    println(((a % c) + (b % c)) % c)
    println((a * b) % c)
    println(((a % c) * (b % c)) % c)
}

fun main(args: Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val token = StringTokenizer(br.readLine())

    rest(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()),Integer.parseInt(token.nextToken()))

    br.close()

}
