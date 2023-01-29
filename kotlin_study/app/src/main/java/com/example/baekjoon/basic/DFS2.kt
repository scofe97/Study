package com.example.baekjoon.basic

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))

private val st = StringTokenizer(br.readLine())

private var n = st.nextToken().toInt() // n 개중에
private var m : Int = st.nextToken().toInt() // 뽑을 개수

private var dy = Array<IntArray>(n+1){IntArray(n+1)}

private fun dfs(n:Int, m: Int): Int {

    // 메모제이션을 이용해서 이미 수가 차있다면 그대로 사용
    if (dy[n][m]>0) return dy[n][m]

    // nC0=1, nCn=1이기 때문에 종료 조건
    return if (m==0 || n==m) 1

    else {
        // 메모제이션 이용
        dy[n][m]= dfs(n-1,m-1)+dfs(n-1,m)
        dy[n][m]
    }
}

private fun main(){

    bw.write("${dfs(n,m)}")
    bw.flush()
    bw.close()
    br.close()

}
