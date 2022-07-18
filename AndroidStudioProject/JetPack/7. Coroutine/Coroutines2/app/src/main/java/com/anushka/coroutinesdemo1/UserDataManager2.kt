package com.anushka.coroutinesdemo1

import kotlinx.coroutines.*

class UserDataManager2 {

    suspend fun getTotalUserCount() : Int{
        var count = 0
        lateinit var deferred : Deferred<Int>

        // 이 범위는 자식 범위내의 모든 작업완료를 보장함
        coroutineScope {
            launch(Dispatchers.IO) {
                delay(1000)
                count = 50
            }

            deferred = async(Dispatchers.IO) {
                delay(3000)
                return@async 70
            }
        }

        return count + deferred.await()
    }
}