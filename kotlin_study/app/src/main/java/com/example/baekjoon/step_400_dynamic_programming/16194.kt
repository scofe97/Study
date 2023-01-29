package com.example.baekjoon.step_400_dynamic_programming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.min

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    val cardPack = br.readLine().toInt()
    val priceList = mutableListOf<Int>()
    val st = StringTokenizer(br.readLine())
    priceList.add(0)

    repeat(cardPack){
        val price = st.nextToken().toInt()
        priceList.add(price)
    }

    for(i in 2..cardPack){
        priceList[i] = minPrice(priceList, i)
    }

    bw.write(priceList[cardPack].toString())
    bw.flush()
    bw.close()
    br.close()
}

fun minPrice(list:MutableList<Int>, idx:Int) : Int{
    for(i in 1..idx/2){
        if(list[idx] > list[idx-i]+list[i]) list[idx] = list[idx-i]+list[i]
    }
    return list[idx]
}