package com.example.baekjoon.step_500_brute_force

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.abs
import kotlin.math.min


fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    val input = br.readLine()
    val goal = Integer.parseInt(input)
    val goalLen =input.length

    var n = Integer.parseInt(br.readLine())

    // 목적지가 이동해야하는 값
    var answer = abs(goal-100)

    // 금지된 버튼
    val nope = BooleanArray(10)

    if(n!=0) {
        val tk = StringTokenizer(br.readLine())
        while (n-- != 0) {
            nope[Integer.parseInt(tk.nextToken())]=true
        }
    }
    else{
        // 금지된 번호가 없는경우
        // 해당 목적지 입력이 빠른가? 혹은 +,- 를 누르는게 더 빠른가?
        bw.write("${min(goalLen,answer)}")
        bw.flush()
        bw.close()
        br.close()
        return
    }

    // 이동채녈은 50000이므로 해당 자리수의 최대치인 99999의 경우의수를 구한다.
    for( i in 0 .. 1000000){

        var num =i
        var canCheck =true
        var cnt=0

        // ex 1521이 들어왔다
        // 1의 자리수부터 금지된 번호가 있는지 확인
        while(num>0){
            if(nope[num%10]){
                canCheck=false
                break
            }
            cnt++
            num/=10
        }

        // 0번으로 이동하는 경우
        if(i==0) {
            cnt = 1
            if(nope[i]) {
                canCheck = false
            }
        }

        // canCheck 가 트루이면 값을 구함
        // answer -> 단순 + - 로 움직이는게 더 빠른가
        // abs(goal-i)+cnt) 금지되지 않은번호에서 + - 로 움직이는게 더 빠른가.
        if(canCheck){
            answer = min(answer, abs(goal-i)+cnt)
        }
    }

    bw.write("$answer")
    bw.flush()
    bw.close()
    br.close()
}