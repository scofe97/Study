package com.example.kotlin_study.function

import android.os.Build
import androidx.annotation.RequiresApi

const val POINTS_X_PASS: Int = 15

// 변경가능한 Map
val EZPassAccounts: MutableMap<Int, Int> = mutableMapOf(1 to 100, 2 to 100, 3 to 100)   // 1

// 변경 불가능 Map
val EZPassReport: Map<Int, Int> = EZPassAccounts                                        // 2

// Id 값이 있으면 값 수정한다.
fun updatePointsCredit(accountId: Int) {
    if (EZPassAccounts.containsKey(accountId)) {                                        // 3
        println("Updating $accountId...")
        EZPassAccounts[accountId] = EZPassAccounts.getValue(accountId) + POINTS_X_PASS  // 4
    } else {
        println("Error: Trying to update a non-existing account (id: $accountId)")
    }
}

// Map 값을 출력한다.
@RequiresApi(Build.VERSION_CODES.N)
fun accountsReport() {
    println("EZ-Pass report:")
    EZPassReport.forEach {                                                              // 5
            (k, v) -> println("ID $k: credit $v")
    }
}

@RequiresApi(Build.VERSION_CODES.N)
fun main() {
    accountsReport()                                                                    // 6
    updatePointsCredit(1)                                                               // 7
    updatePointsCredit(1)
    updatePointsCredit(5)                                                               // 8
    accountsReport()                                                                    // 9
}

val test : MutableMap<Int,Int> = mutableMapOf(1 to 100, 2 to 100, 3 to 100)

val test2 : Map<Int,Int> = test

fun test3(accountId: Int){
    if(test.containsKey(accountId)){
        println("키값 존재")
        test[accountId] = test.getValue(accountId) + POINTS_X_PASS
    }
    else{
        println("키값 없음")
    }
}