package com.example.baekjoon.step_200_data_strcture

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    var st = StringTokenizer(br.readLine())
    val commandSize = st.nextToken().toInt()

    repeat(commandSize){
        st =  StringTokenizer(br.readLine())

        var lSize = 0
        var rSize = 0

        val token = st.nextToken().chunked(1)
        for(item in token){

            if(lSize < rSize){
                bw.write("NO\n")
                return@repeat
            }
            when(item){
                "(" -> lSize++
                ")" -> rSize++
            }
        }
        bw.write(if(lSize == rSize) "YES\n" else "NO\n")
    }

    bw.flush()
    bw.close()
    br.close()
}

