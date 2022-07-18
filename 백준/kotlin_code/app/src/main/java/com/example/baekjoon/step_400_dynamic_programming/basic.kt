package com.example.baekjoon


fun memorization(n:Int) : Int{

    val list = MutableList<Int>(n+2){i->i}

    list[0] = 1
    list[1] = 1

    if(n < 2) return list[n]

    for(i in 2 until n+1) list[i] = list[i-2] + list[i-1]

    return list[n]
}

fun bottomUp(n:Int) : Int{

    if(n <= 1) return n

    var fir = 0
    var sec = 1
    var next = 0

    for( i in 0 until n-1){
        next = fir+sec
        fir = sec
        sec = next
    }
    return next
}

fun topDown(n:Int) : Int{

    val list = MutableList<Int>(n+2){i->i}

    list[0] = 1
    list[1] = 1

    if(n < 2) return list[n]

    list[n] = topDown(n-1) + topDown(n-2)
    return list[n]
}