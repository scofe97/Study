package com.example.baekjoon

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    while(true){
        val line = br.readLine()
        println(line.isEmpty())
        if(line == null || line.equals("")){
            break
        }
        val st = StringTokenizer(line)
        bw.write(one(st.nextToken().toInt()) + "\n")

    }

    bw.flush()
    bw.close()
    br.close()
}

fun one(number: Int): String {
    var i = 1
    var divide = 1

    while (divide % number != 0 ) {
        divide %= number
        divide = (divide * 10) + 1
        i++
    }
    return "$i"
}


/*
val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()
var input: String? = br.readLine()
while (input != null) {
    val num = input!!.toLong()
    var ans = 1L
    var cnt = 1

    while (true) {
        if (ans % num == 0L) {
            bw.write("$cnt\n")
            break
        }
        ans %= num
        ans = (ans * 10) + 1
        cnt++
    }

    bw.flush()

    input = br.readLine()
}*/
