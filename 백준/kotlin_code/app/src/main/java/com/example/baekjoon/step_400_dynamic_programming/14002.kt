package com.example.baekjoon.step_400_dynamic_programming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.max


fun main(){

    // 입출력에 사용하는 변수
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    // 수열의 크기
    val count = br.readLine().toInt()

    // 수열의 요소를 받아서 List<Int> 형태로 생성
    val list : List<Int> = br.readLine().split(" ").map { it.toInt() }.toList()

    // 첫번째 줄 출력을 위한 각 단계의 수열길이를 담을 리스트
    val listResult = MutableList(count){ _->1}


    // 수열을 돌며 list[i] 가 list[n] 보다 크면 listResult[n]을 +1 해서 넣어줌
    for(i in 0 until count){
        for( n in 0 until i){
            if(list[i] > list[n]){
                listResult[i] = max(listResult[i], listResult[n]+1)
            }
        }
    }

    val result = listResult.maxOrNull()
    var temp = result

    val s = Stack<Int>()

    for(i in count-1 downTo 0){
        if(listResult[i] == temp){
            s.push(list[i])
            temp--
        }
    }

    bw.write("$result \n")
    while (s.isNotEmpty()){
        bw.write("${s.pop()} ")
    }


    bw.flush()
    bw.close()
    br.close()

}

/*
7
3 1 5 2 3 6 4
*/
