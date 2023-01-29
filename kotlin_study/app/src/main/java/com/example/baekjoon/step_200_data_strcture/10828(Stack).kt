package com.example.baekjoon.step_200_data_strcture


import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var st = StringTokenizer(br.readLine())
    val commandSize = st.nextToken().toInt()

    val stack = Stack()

    repeat(commandSize){
        st =  StringTokenizer(br.readLine())

        when(st.nextToken()){
            "push" -> stack.push(st.nextToken().toInt())
            "pop" -> bw.write(stack.pop().toString() + "\n")
            "size" -> bw.write(stack.size().toString() + "\n")
            "empty" -> bw.write(stack.empty().toString() + "\n")
            "top" -> bw.write(stack.top().toString() + "\n")
        }
    }

    bw.flush()
    bw.close()
    br.close()
}

