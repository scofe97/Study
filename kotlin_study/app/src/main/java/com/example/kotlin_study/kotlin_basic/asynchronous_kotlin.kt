package com.example.kotlin_study.kotlin_basic

import kotlinx.coroutines.*

// Caller (호출을 하는 객체)가 Callee(호출된 객체)에게로 넘겨준 다음 Callee 가 콜백 함수를 호출하면
// Caller 에서 결과를 받는다.

//  callee 로 callback 이라는 콜백 함수를 넘겨주고 해당 콜백 함수는 Caller 인 main 함수에서 실행됨



data class User(val name: String, val id: Int) {
    // 1
    override fun equals(other: Any?) =
        other is User && other.id == this.id
}

suspend fun main(){


    val user = User("Sim", 1)

    val secondUser = User("Sim", 1)
    val thirdUser = User("Max", 2)

    println("user == secondUser: ${user == secondUser}")
    println("user == thirdUser: ${user == thirdUser}")


    println(user.hashCode())                               // 5
    println(secondUser.hashCode())
    println(thirdUser.hashCode())


    println(user.copy())                                   // 6
    println(user === user.copy())                          // 7
    println(user.copy("Max"))                              // 8
    println(user.copy(id = 3))                             // 9

    println("name = ${user.component1()}")                 // 10
    println("id = ${user.component2()}")

    println(user.toString())

    println(user == secondUser) // equals 와 동일


    val user1 = User("sim", 5)

    val (name, id) = user1

    println("$name ,  $id")


    println("name = ${user.component1()}")                 // 10
    println("id = ${user.component2()}")


/*    val job1 : Job = scope.launch {
        var i = 0
        while (i < 10) {
            println("job 1 : $i")
            delay(500)
            i++
        }
    }

    val job2 : Job = scope.launch {
        var i = 0
        while (i < 10) {
            println("job 2 : $i")
            delay(1000)
            i++
        }
    }*/

/*    val job3 : Job = scope.launch {
        var i = 0
        while(i < 10){
            println("job 3 : $i")
            delay(500)
            i++
        }
    }

    scope.launch(job3) {
        var i = 0
        while (i < 10) {
            println("job 3 in : $i")
            delay(1000)
            i++
        }
    }

    job3.join()*/



/*    val deferred : Deferred<Int> = scope.async {
        var i = 0
        while (i < 10) {
            delay(500)
            i++
        }

        return@async i
    }

    val msg = deferred.await()
    println(msg) // result 출력*/



/*    val deferred1 = scope.async {
        var i = 0
        while (i < 10) {
            delay(500)
            i++
        }

        i
    }

    val deferred2 = scope.async {
        var i = 0
        while (i < 10) {
            delay(1000)
            i++
        }

        i
    }

    val result1 = deferred1.await()
    val result2 = deferred2.await()

    println("$result1 , $result2") // result1 , result 2 출력*/

/*    val job = scope.launch(start = CoroutineStart.LAZY){

    }

    val deferred = scope.async(start = CoroutineStart.LAZY) {

    }*/

/*    println("start")

    val deferred = scope.async(start = CoroutineStart.LAZY) {
        var i = 0
        println("시작")
        println("$i")
        while (i < 5) {
            println("lazy async $i")
            delay(500)
            i++
        }
    }

    deferred.start()

    println("end")*/

}