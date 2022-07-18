package com.example.baekjoon.step_500_brute_force

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    // M N x y
    val count = br.readLine().toInt()

    repeat(count) {
        val st = StringTokenizer(br.readLine())

        val m = st.nextToken().toInt()
        val n = st.nextToken().toInt()
        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()

        val maxYear = lcm(m,n)
        var result = x

        while(true){
            if(result > maxYear || (result)% n == y) break
            if(y == n && (result)% n == 0 ) break

            result += m

        }

        if(result > maxYear) bw.write("-1\n") else bw.write("$result\n")

    }

    bw.flush()
    bw.close()
    br.close()

}

fun gcd(a:Int, b:Int):Int{
    return if(b == 0) a else gcd(b,a%b)
}

fun lcm(a:Int, b:Int):Int{
    return (a * b) / gcd(a,b)
}